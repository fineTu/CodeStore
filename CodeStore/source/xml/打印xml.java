package com.huashu.boss.busi.ei.dto.selfServ;

import java.io.StringWriter;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;


public class RegisterRequestInfo {
	
	public static void main(String args[]){
		Document document = DocumentHelper.createDocument();
		Element book = document.addElement("book");
		book.addAttribute("author", "老舍");
		book.addAttribute("name", "老张的哲学");
		book.addAttribute("price", "20");
		
		StringWriter writer = new StringWriter();
		OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("ISO-8859-1");
		XMLWriter xmlwriter = new XMLWriter(writer, format);
	        try {
	            xmlwriter.write(document);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		System.out.println(writer.toString());
	}
}
