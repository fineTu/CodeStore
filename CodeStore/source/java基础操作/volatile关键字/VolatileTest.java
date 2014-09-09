package com.finetu.test;

public class VolatileTest {
	private static VolatileValue value = new VolatileValue();
	public static void main(String args[]){
		for(int i=0;i<2;i++){
			Thread thread = new Thread(new VolatileRunner(value, i%2==0?true:false));
			thread.start();
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(value.getValue());
	}
}
