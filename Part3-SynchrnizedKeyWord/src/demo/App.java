package demo;

public class App {

	int count=0;
	public static void main(String [] args) throws InterruptedException{
		
		App app= new App();
		app.doWork();
		
		
	}
	 synchronized public void increament(){
		count++;
	}
	public void doWork() throws InterruptedException{
		Thread t1= new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(int i=0;i<10000;i++){
					increament();
				}
			}
		});
		
		Thread t2= new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(int i=0;i<10000;i++){
					increament();
				}
			}
		});
		t1.start();
		t2.start();
		
		t2.join();
		t2.join();
		
		System.out.println("count is"+count);
	}
}
