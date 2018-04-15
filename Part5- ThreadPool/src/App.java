import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor implements Runnable{

	
	int taskId;
	
	 public Processor(int taskId) {
		// TODO Auto-generated constructor stub
		 this.taskId=taskId;
	}
	@Override
	public void run() {
		
		//for(int i=0;i<10;i++){
			System.out.println("starting"+taskId);
			URL obj = null;
			try {
				obj = new URL("http://www.republicworld.com/");
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			HttpURLConnection con = null;
			try {
				con = (HttpURLConnection) obj.openConnection();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				con.setRequestMethod("GET");
			} catch (ProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con.setRequestProperty("User-Agent","Mozilla/5.0");
			try {
				int responseCode = con.getResponseCode();
				System.out.println(responseCode);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("completed"+taskId);
		//}
	}
	
}


public class App {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService executorService= Executors.newFixedThreadPool(5);
		for(int i=0;i<10;i++){
			executorService.submit(new Processor(i));
		}
		executorService.shutdown();
		System.out.println("All task submited");
		executorService.awaitTermination(1,TimeUnit.DAYS);
	}

}
