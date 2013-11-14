package com.demo.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletRequestContext;

public class FileUploadServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		FileUpload fu = new FileUpload(factory);
		try {
			List<FileItem> fileItems = fu.parseRequest(new ServletRequestContext(request));
			for(FileItem item : fileItems){
				if(!item.isFormField()){
					File path = new File(request.getSession().getServletContext().getRealPath("/")+System.currentTimeMillis());
					path.mkdirs();
					int index = item.getName().lastIndexOf("\\");
					String name = item.getName().substring(index+1);
					System.out.println(name);
					File f = new File(path,name);
					System.out.println(f.getAbsolutePath());
					item.write(f);
				}
			}
			response.getWriter().write("SUCCESS");
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
