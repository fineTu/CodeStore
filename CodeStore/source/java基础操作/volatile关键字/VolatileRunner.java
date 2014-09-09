package com.finetu.test;

public class VolatileRunner implements Runnable{
	private VolatileValue value = null;
	private boolean isAdd = true;
	public VolatileRunner(VolatileValue value,boolean isAdd){
		this.value = value;
		this.isAdd = isAdd;
	}
	@Override
	public void run() {
		for(int i=0;i<100;i++){			
			int v = value.getValue();
			System.out.println(isAdd?"+++++++":"-------");
			value.setValue(isAdd?v+100:v-100);
			try {
				Thread.sleep((int)(Math.random()*10));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
