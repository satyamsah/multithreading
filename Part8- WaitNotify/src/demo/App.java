package demo;

import java.util.Scanner;

class Manager{
	
	void manage() throws InterruptedException{
		Thread produceThread= new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					producer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		Thread consumeThread = new Thread(new Runnable(){
			@Override
			public void run() {
				try {
					consumer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		produceThread.start();
		consumeThread.start();
		produceThread.join();
		consumeThread.join();
		
	}
	
	private void producer() throws InterruptedException{
		synchronized (this) {
			System.out.println("producer Started.. "+"object name: "+this.hashCode()+ "    thread name "+Thread.currentThread().getName());
			wait();
			System.out.println("producer completed..");
		}
	}
	
	private void consumer() throws InterruptedException{
		synchronized (this) {
			Thread.sleep(1000);
			System.out.println("Consumer Started.."+"object name: "+this.hashCode()+ "   thread name "+Thread.currentThread().getName());
			Scanner sc=new Scanner(System.in);
			sc.nextLine();
			sc.close();
			System.out.println("Consumer completed ");
			notify();
			Thread.sleep(5000);
			
		}
	}
}



public class App {
	
	public static void main(String[] args) throws InterruptedException{
		Manager manager= new Manager();
		manager.manage();
	}

}
