package demo;

import java.util.*;

public class App {

	static List<Integer> list= new LinkedList<>();
	static Object lock=new Object();
	static int LIMIT=10;
	
	public static void produce() throws InterruptedException{
		synchronized (lock) {
			while(true){
				if(list.size()==LIMIT){
					lock.wait();
				}
				int addednumber= new Random().nextInt(10000);
				list.add(addednumber);
				System.out.println("added number "+addednumber+"ll size"+list.size());
				lock.notify();
			}
		}
		
	}
	public static void consume() throws InterruptedException{
		synchronized (lock) {
			while(true){
				if(list.size()==0){
					lock.wait();
				}
				
				int removednumber=list.remove(0);
				System.out.println("removed number "+removednumber+"ll size"+list.size());
				Thread.sleep(500);
				lock.notify();
			}
			
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		Thread producer= new Thread(new Runnable() {
			
			@Override
			public void run() {
			try {
				produce();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			}
		});
		
		
		Thread consumer= new Thread(new Runnable() {
			
			@Override
			public void run() {
			try {
				consume();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			}
		});
		
		producer.start();
		consumer.start();
		
	}

}
