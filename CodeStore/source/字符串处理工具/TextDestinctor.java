package com.finetu.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TextDestinctor {
	public static void main(String args[]){
		File file = new File("D:/text.txt");
		try {
			FileReader fr = new FileReader(file);
			BufferedReader bf = new BufferedReader(fr);
			String data = bf.readLine();
			List<String> list = new ArrayList<String>();
			while(data!=null){
				if(!list.contains(data)){
					list.add(data);
				}
				
				data = bf.readLine();
			}
			for(String s:list){				
				System.out.println(s);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
