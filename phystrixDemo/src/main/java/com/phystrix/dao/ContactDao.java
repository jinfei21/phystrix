package com.phystrix.dao;

import com.guard.phystrix.Phystrix;
import com.phystrix.model.Contact;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
public class ContactDao {
    private Logger logger = LoggerFactory.getLogger(ContactDao.class);

    @Phystrix(commandKey="getContact",commandGroup="contact",fallBack="contactFallBack")
    public Contact getContact(String customerId) {
        logger.info("Get contact for customer {}", customerId);
        
        //测试用
        if(customerId.equals("-2")){
	        Random random = new Random();
			int num = random.nextInt(1500);
			if(num < 500){
				throw new RuntimeException("throw get contact exception!");
			}
			try {
				TimeUnit.MILLISECONDS.sleep(num);
			} catch (InterruptedException e) {}
        }
        
        System.out.println("=========cccccccccccccccc");
        return new Contact("010-58888888","13800000000","xxx@163.com");
    }
    
    public Contact contactFallBack(String customerId){
		System.out.println("-----fallback: get contact failed!");
		return null;
	}
}
