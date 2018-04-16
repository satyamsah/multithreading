package demo;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Producer implements Runnable{

	BlockingQueue<Integer> queue;
	
	public Producer(BlockingQueue<Integer> queue) {
		// TODO Auto-generated constructor stub
		this.queue=queue;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			
			while(true){
				Thread.sleep(1000);
				int number=new Random().nextInt(10000);
				queue.put(number);
				System.out.println("added to the queue"+number);
				
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}

class Consumer implements Runnable{

	BlockingQueue<Integer> queue;
	public Consumer(BlockingQueue<Integer> queue){
		this.queue=queue;
	}
	public void run() {

		while(true){
			try {
				Thread.sleep(5000);
				int val = queue.take();
				System.out.println("value taken is "+val+" que size is "+queue.size());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		// TODO Auto-generated method stub
		
	}
	
}

class Manager {
	
	void pub_sub() throws InterruptedException{
		BlockingQueue<Integer> blockingQueue= new ArrayBlockingQueue<>(10);
		ExecutorService executorService= Executors.newFixedThreadPool(2);
		for(int i=0;i<10;i++){
			executorService.submit(new Consumer(blockingQueue));
		}
		Thread producer= new Thread(new Producer(blockingQueue));
		//Thread consumer= new Thread(new Consumer(blockingQueue));
		producer.start();
		//consumer.start();
		producer.join();
		//consumer.join();
		
	}
	
	
}
public class App {

	public static void main(String[] args) throws InterruptedException {
		Manager manager= new Manager();
		manager.pub_sub();
			
	}

}
