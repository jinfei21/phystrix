package com.guard.phystrix.testcase;

import java.util.concurrent.TimeUnit;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.guard.phystrix.Phystrix;
import com.netflix.config.ConfigurationManager;

import lombok.extern.slf4j.Slf4j;



@Component
@Slf4j
public class Test {

	@Phystrix(commandKey="key",commandGroup="group",fallBack="requestFallBack",maxRequest=300)
    public String fn(String hello,int num) throws Exception {
		TimeUnit.MILLISECONDS.sleep(2000);
        System.out.println("123" + hello + num);
        return "ok";
    }

	public String requestFallBack(String hello,int num){
		System.out.println("------requestFallBack: fall back!");
		return "failed";
	}
	
    public static void main(String[] args) throws Exception{
//    	System.setProperty("archaius.deployment.applicationId", "phystrix");
    	
    	
    	
        ClassPathXmlApplicationContext ctx= new ClassPathXmlApplicationContext("classpath:phystrix.xml");
        ctx.refresh();
        ConfigurationManager.loadAppOverrideProperties("phystrix");

        System.out.println("!!!Start!!!");
        long startTime = System.currentTimeMillis();
        Test test= ctx.getBean(Test.class);
        String result= test.fn("helloworld",123);
        System.out.println("result = " + result);
        System.out.println("!!!OK!!! Spent time = " + (System.currentTimeMillis() - startTime) + "MS");
//        ctx.close();
    }
}
