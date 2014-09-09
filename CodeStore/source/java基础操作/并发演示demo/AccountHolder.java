package com.finetu.test;

import java.util.concurrent.locks.ReentrantLock;

public class AccountHolder {
	private int[] accounts = null;
	private ReentrantLock lock;
	public AccountHolder(int[] accounts){
		this.accounts = accounts;
		lock = new ReentrantLock();
	}
	public void transfer(int fromIndex,int toIndex,int amount){
		lock.lock();
		try{
			if(accounts[fromIndex]<amount){
				return;
			}	
			accounts[fromIndex]-=amount;
			System.out.printf("%d from %d to %d\n", amount,fromIndex, toIndex);
			accounts[toIndex]+=amount;
		}finally{
			int sum = 0;
			for(int n:accounts){
				if(n<0){
					System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				}
				sum+=n;
			}
			System.out.println("\n-----------"+sum);
			lock.unlock();
		}
		
//		if(accounts[fromIndex]<amount){
//			return;
//		}
//		accounts[fromIndex]-=amount;
//		System.out.printf("%d from %d to %d\n", amount,fromIndex, toIndex);
//		accounts[toIndex]+=amount;
	}
	
	public int getTotal(){
		int sum = 0;
		for(int n:accounts){
			sum+=n;
		}
		return sum;
	}
	public int size(){
		return accounts.length;
	}
}
