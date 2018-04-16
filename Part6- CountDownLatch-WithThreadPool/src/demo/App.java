package demo;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class MasterTask{
	
	public void spawnSelf() throws InterruptedException{
		CountDownLatch latch = new CountDownLatch(10); // you need to count the latch down it down to zero, 
		ExecutorService executorService= Executors.newFixedThreadPool(10);
		for(int i=0;i<10;i++){
			executorService.submit(new Worker(latch, 10, "Worker1"+i));
			
		}
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
		System.out.println("started "+workerName+":"+ Thread.currentThread().getName());
		
		
	}
	
}
public class App {

	public static void main(String[] args) throws InterruptedException {
		MasterTask masterTask= new MasterTask();
		masterTask.spawnSelf();

	}

}
