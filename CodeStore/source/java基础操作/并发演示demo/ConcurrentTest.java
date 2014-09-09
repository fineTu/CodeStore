package com.finetu.test;

public class ConcurrentTest {
	public static int[] accounts = {1000,1000,1000,1000};
	
	public static void main(String args[]){
		AccountHolder accountHolder = new AccountHolder(accounts);
		for(int i=0;i<accounts.length*3;i++){
			Thread t = new Thread(new Executor(accountHolder,i%accounts.length));
			t.start();
		}
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int sum = 0;
		for(int n:accounts){
			System.out.print(n+",");
			sum+=n;
		}
		System.out.println("\n-----"+sum);
	}
	
	
}
