package com.guard.phystrix.testcase;

import java.util.concurrent.TimeUnit;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.guard.phystrix.Phystrix;

import lombok.extern.slf4j.Slf4j;



@Component
@Slf4j
public class Test {

	@Phystrix(commandKey="key",commandGroup="group",fallBack="requestFallBack",isolationStgy="thread")
    public String fn(String hello,int num) throws Exception {
		TimeUnit.SECONDS.sleep(2);
        System.out.println("123" + hello + num);
        return "ok";
    }

	public String requestFallBack(String hello,int num){
		System.out.println("requestFallBack: fall back!");
		return "failed";
	}
	
    public static void main(String[] args) throws Exception{
        ClassPathXmlApplicationContext ctx= new ClassPathXmlApplicationContext("classpath:phystrix.xml");
        ctx.refresh();

        log.error("eeeee");
        System.out.println("!!!Start!!!");
        long startTime = System.currentTimeMillis();
        Test test= ctx.getBean(Test.class);
        String result= test.fn("helloworld",123);
        System.out.println("result = " + result);
        System.out.println("!!!OK!!! Spent time = " + (System.currentTimeMillis() - startTime) + "MS");
        ctx.close();
    }
}
