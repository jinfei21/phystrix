package controller;



import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.http.client.fluent.Request;
import org.junit.Test;

public class UserControllerTest {

	@Test
	public void test() throws Exception {
		String response;
		Random random = new Random();
		int index = 0;
		while(true){
			index = random.nextInt(10);
			response = Request.Get("http://localhost:8140/phystrix-demo/user/"+index+"/profile")
//				      .connectTimeout(2000)
//				      .socketTimeout(2000)
				      .execute()
				      .returnContent()
				      .asString();
				      
	      System.out.println(response);	
	      TimeUnit.MILLISECONDS.sleep(1000);
		}
	}
	
	
}
