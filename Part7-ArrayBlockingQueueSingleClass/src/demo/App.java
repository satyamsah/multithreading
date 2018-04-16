package demo;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class App  {

	static BlockingQueue<Integer> blockingQueue= new ArrayBlockingQueue<>(10);
	public static void produce() throws InterruptedException{
		while(true){
			Thread.sleep(100);
			int addednumber=new Random().nextInt(10000);
			blockingQueue.put(addednumber);
			System.out.println("producer---added number is "+addednumber+" queue size"+blockingQueue.size() );
		}
	}
	public static void consume() throws InterruptedException{
		while(true){
			Thread.sleep(1000);
			int removednumber=blockingQueue.take();
			System.out.println("consumer---removed number is "+removednumber+" queue size"+blockingQueue.size() );
		}
		
	}
	public static void main(String []args) throws InterruptedException {
		
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
		consumer.start();
		producer.start();
		consumer.join();
		producer.join();
	}
}
