package com.finetu.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class TextCompare {
	public static void main(String args[]){
		try {
			FileReader fr1 = new FileReader("D:/text1.txt");
			BufferedReader bf1 = new BufferedReader(fr1);
			FileReader fr2 = new FileReader("D:/text2.txt");
			BufferedReader bf2 = new BufferedReader(fr2);
			Set<String> set1 = new HashSet<String>();
			Set<String> set2 = new HashSet<String>();
			String data = bf1.readLine();
			Set<String> set = new HashSet<String>();
			while(data!=null){
				set.add(data.trim());
				set1.add(data.trim());
				data = bf1.readLine();
			}
			data = bf2.readLine();
			while(data!=null){
				set.add(data.trim());
				set2.add(data.trim());
				data = bf2.readLine();
			}
			set1.removeAll(set2);
			for(String s:set1){				
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
