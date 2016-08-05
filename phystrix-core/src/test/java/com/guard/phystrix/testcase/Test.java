package com.guard.phystrix.testcase;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.guard.phystrix.Phystrix;

import lombok.extern.slf4j.Slf4j;



@Component
@Slf4j
public class Test {

	@Phystrix(commandKey="key",commandGroup="group",fallBack="requestFallBack")
    public String fn(String hello,int num) {
        System.out.println("123" + hello + num);
        return "ok";
    }

    public static void main(String[] args){
        ClassPathXmlApplicationContext ctx= new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        ctx.refresh();

        log.error("eeeee");
        System.out.println("!!!Start!!!");
        long startTime = System.currentTimeMillis();
        Test test= ctx.getBean(Test.class);
        String result= test.fn("helloworld",123);
        System.out.println("result = " + result);
        System.out.println("!!!OK!!! Spent time = " + (System.currentTimeMillis() - startTime) + "MS");
    }
}
