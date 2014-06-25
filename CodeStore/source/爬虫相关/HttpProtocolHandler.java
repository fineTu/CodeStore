package com.moji.util.common.httpclient;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.FilePartSource;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.util.IdleConnectionTimeoutThread;

public class HttpProtocolHandler {
	//连接超时时间，缺省为8秒钟 */
    private int defaultConnectionTimeout = 8000;
    //回应超时时间, 缺省为30秒钟 */
    private int defaultResponseTimeout = 30000;
    //闲置连接超时时间, 缺省为60秒钟 
    private int defaultIdleConnTimeout = 60000;
    //线程池缺省每个主机最大连接数
    private int defaultMaxConnPerHost = 30;
    //线程池缺省的最大连接数
    private int defaultMaxTotalConn = 80;

    /** 默认等待HttpConnectionManager返回连接超时（只有在达到最大连接数时起作用）：1秒*/
    private static final long defaultHttpConnectionManagerTimeout = 3 * 1000;

    /**
     * HTTP连接管理器，该连接管理器必须是线程安全的.
     */
    private HttpConnectionManager connectionManager;

    private static HttpProtocolHandler httpProtocolHandler = new HttpProtocolHandler();

    public static HttpProtocolHandler getInstance() {
        return httpProtocolHandler;
    }

    private HttpProtocolHandler() {
        // 创建一个线程安全的HTTP连接池
        connectionManager = new MultiThreadedHttpConnectionManager();
        connectionManager.getParams().setDefaultMaxConnectionsPerHost(defaultMaxConnPerHost);
        connectionManager.getParams().setMaxTotalConnections(defaultMaxTotalConn);

        IdleConnectionTimeoutThread ict = new IdleConnectionTimeoutThread();
        ict.addConnectionManager(connectionManager);
        ict.setConnectionTimeout(defaultIdleConnTimeout);

        ict.start();
    }

    /**
     * 执行get请求
     * @param request
     * @return
     */
    public HttpResponse executeGet(HttpRequest request) {
    	HttpClient httpclient = createHttpClient(request);
    	HttpMethod method = new GetMethod(request.getUrl());
    	//设置字符集
    	String charset = request.getCharset();
    	method.getParams().setCredentialCharset(charset);
    	//设置参数
    	String queryString = generatQueryString(request.getParamMap());
    	method.setQueryString(queryString);    	
    	
    	return execute(httpclient, method, request.getResultType());
    }
    
    /**
     * 执行post请求
     * @param request
     * @return
     */
    public HttpResponse executePost(HttpRequest request) {
    	HttpClient httpclient = createHttpClient(request);
    	PostMethod method = new PostMethod(request.getUrl());
    	
    	//设置字符集
    	String charset = request.getCharset();
    	method.addRequestHeader("Content-Type", "application/x-www-form-urlencoded; text/html; charset=" + charset);
    	
    	//设置参数
    	NameValuePair[] parameters = this.generatNameValuePair(request.getParamMap());
    	method.addParameters(parameters);
    	
    	return execute(httpclient, method, request.getResultType());
    }
    
    /**
     * 执行post带附件请求
     * @param request
     * @return
     */
    public HttpResponse executePost(MutilHttpRequest request) {
    	HttpClient httpclient = createHttpClient(request);
    	PostMethod method = new PostMethod(request.getUrl());
    	
    	//设置字符集
    	String charset = request.getCharset();    	    	
    	//设置普通参数
    	List<Part> parts = generatStringPart(request.getParamMap(), charset);
    	//设置附件参数
    	try {
			parts.add(new FilePart(request.getFileName(), new FilePartSource(request.getFile())));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    	    	
    	method.setRequestEntity(new MultipartRequestEntity(parts.toArray(new Part[parts.size()]), new HttpMethodParams()));    	
    	return execute(httpclient, method, request.getResultType());
    }
    
    public HttpResponse executePost(ByteHttpRequest request) {
    	HttpClient httpclient = createHttpClient(request);
    	PostMethod method = new PostMethod(request.getUrl());
    	
    	//设置字符集
//    	String charset = request.getCharset();
    	//method.addRequestHeader("Content-Type", "application/x-www-form-urlencoded; text/html; charset=" + charset);
    	method.setRequestEntity(new ByteArrayRequestEntity(request.getRequestBody()));
    	return execute(httpclient, method, request.getResultType());
    }
   
    private HttpClient createHttpClient(HttpRequest request) {
    	 HttpClient httpclient = new HttpClient(connectionManager);
         //设置连接超时
         int connectionTimeout = defaultConnectionTimeout;
         if (request.getConnectionTimeout() > 0) {
             connectionTimeout = request.getConnectionTimeout();
         }
         httpclient.getHttpConnectionManager().getParams().setConnectionTimeout(connectionTimeout);

         // 设置回应超时
         int responseTimeout = defaultResponseTimeout;
         if (request.getResponseTimeout() > 0) {
         	responseTimeout = request.getResponseTimeout();
         }
         httpclient.getHttpConnectionManager().getParams().setSoTimeout(responseTimeout);

         // 设置等待ConnectionManager释放connection的时间
         httpclient.getParams().setConnectionManagerTimeout(defaultHttpConnectionManagerTimeout);
         
         return httpclient;
    }
    
    private String generatQueryString(Map<String, String> paramMap) {
    	StringBuffer queryString = new StringBuffer();
    	for (Map.Entry<String, String> entry : paramMap.entrySet()) {
    		if (queryString.toString().equals("")) {
    			queryString.append("?").append(entry.getKey()).append("=").append(entry.getValue());
    		} else {
    			queryString.append("&").append(entry.getKey()).append("=").append(entry.getValue());
    		}
    	}
    	
    	return queryString.toString();
    }
    
    private NameValuePair[] generatNameValuePair(Map<String, String> paramMap) {
    	NameValuePair[] nameValuePair = new NameValuePair[paramMap.size()];
    	int i = 0;
    	for (Map.Entry<String, String> entry : paramMap.entrySet()) {
    		nameValuePair[i++] = new NameValuePair(entry.getKey(), entry.getValue());
    	}
    	return nameValuePair;
    }
    
    private List<Part> generatStringPart(Map<String, String> paramMap, String charset) {
    	List<Part> parts = new ArrayList<Part>();
    	for (Map.Entry<String, String> entry : paramMap.entrySet()) {
    		parts.add(new StringPart(entry.getKey(), entry.getValue(), charset));
    	}
    	
    	return parts;
    }
    
    private HttpResponse execute(HttpClient httpclient, HttpMethod method, HttpResultType resultType) {
    	// 设置Http Header中的User-Agent属性
        method.addRequestHeader("User-Agent", "Mozilla/4.0");
        
        HttpResponse response = new HttpResponse();
        try {
			httpclient.executeMethod(method);
			if (resultType.equals(HttpResultType.STRING)) {
			    response.setStringResult(method.getResponseBodyAsString());
			} else if (resultType.equals(HttpResultType.BYTES)) {
			    response.setByteResult(method.getResponseBody());
			}
			return response;
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
            method.releaseConnection();
        }
        return null;
    }    
}
