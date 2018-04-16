package demo;
import java.util.concurrent.CountDownLatch;

class MasterTask{
	
	public void spawnSelf() throws InterruptedException{
		CountDownLatch latch = new CountDownLatch(3);
		Worker worker1= new Worker(latch, 1000, "Worker1");
		Worker worker2= new Worker(latch, 1000, "Worker2");
		Worker worker3= new Worker(latch, 1000, "Worker3");
		Thread t1=new Thread(worker1);
		Thread t2=new Thread(worker2);
		Thread t3=new Thread(worker3);
		//t1.join();t2.join();t3.join();
		t1.start();t2.start();t3.start();
		latch.await();
		System.out.println("Lock aquired by "+ Thread.currentThread().getName());
	}
}
class Worker implements Runnable{
	CountDownLatch latch;
	int sleepTime;
	String workerName;
	
	public Worker(CountDownLatch latch,int sleepTime,String workerName){
		this.latch=latch;
		this.sleepTime=sleepTime;
		this.workerName=workerName;
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		latch.countDown();
		System.out.println(Thread.currentThread().getName());
		
		
	}
	
}
public class App {

	public static void main(String[] args) throws InterruptedException {
		MasterTask masterTask= new MasterTask();
		masterTask.spawnSelf();

	}

}
