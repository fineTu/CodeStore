package com.finetu.test;


class Executor implements Runnable{
	private AccountHolder accountHolder = null;
	private int fromIndex = 0;
	public Executor(AccountHolder accountHolder,int fromIndex){
		this.accountHolder = accountHolder;
		this.fromIndex = fromIndex;
	}
	
	@Override
	public void run() {
		int i = 0;
		while(i++<50){
			int toIndex = new Double(accountHolder.size() * Math.random()).intValue();
			int amount = new Double(500 * Math.random()).intValue();
			accountHolder.transfer(fromIndex, toIndex, amount);
			System.out.println("----->>total:"+accountHolder.getTotal());
			try {
				Thread.sleep((int)(10*Math.random()));
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
		System.out.println("thread"+fromIndex+" done!");
		
	}
	
	
}