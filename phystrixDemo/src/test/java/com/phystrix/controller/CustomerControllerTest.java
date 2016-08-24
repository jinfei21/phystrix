package com.phystrix.controller;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.http.client.fluent.Request;
import org.junit.Test;

public class CustomerControllerTest {
	
	/**
	 * 测试正常请求，结果看dashboard和控制台日志
	 * @throws Exception
	 */
	@Test
	public void testNormalRequest() throws Exception {
		String response;
		int i=0;
		while(i++ < 500){
			response = Request.Get("http://localhost:8140/phystrixDemo/customers/1")
				      .execute()
				      .returnContent()
				      .asString();
				      
	      System.out.println(response);	
		}
		
	}
	
	
	/**
	 * 测试熔断开关打开触发条件，1.满足10s内请求数量阀值 2.请求错误率达到阀值，结果看dashboard
	 * 测试熔断时也走fallback流程，熔断时看控制台日志
	 * @throws Exception
	 */
	@Test
	public void testCircuitOpen() throws Exception {
		String response;
		int i=0;
		while(i++ < 50){
			response = Request.Get("http://localhost:8140/phystrixDemo/customers/-1")
				      .execute()
				      .returnContent()
				      .asString();
				      
	      System.out.println(response);	
		}
		
	}
	
	/**
	 * 测试熔断开关打开，熔断10s后，如果再有正常请求，则熔断开关关闭，结果看dashboard
	 * @throws Exception
	 */
	@Test
	public void testCircuitClose() throws Exception {
		String response;
		int i=0;
		while(i++ < 50){
			response = Request.Get("http://localhost:8140/phystrixDemo/customers/-1")
					.execute()
					.returnContent()
					.asString();
			
	      System.out.println(response);	
		}
		 TimeUnit.MILLISECONDS.sleep(10000);
		while(i++ < 100){
			response = Request.Get("http://localhost:8140/phystrixDemo/customers/2")
					.execute()
					.returnContent()
					.asString();
			
			System.out.println(response);	
			TimeUnit.MILLISECONDS.sleep(200);
		}
		
	}
	

}
