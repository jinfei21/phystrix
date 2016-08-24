package com.phystrix.dao;

import com.guard.phystrix.Phystrix;
import com.phystrix.model.Address;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
public class AddressDao {
    private Logger logger = LoggerFactory.getLogger(AddressDao.class);

    @Phystrix(commandKey="getAddress",commandGroup="address",fallBack="adressFallBack")
    public Address getAddress(String customerId) {
        logger.info("Get address for customer {}", customerId);
        
        //测试用
        if(customerId.equals("-1")){
        	System.out.println("use for test: customerId=-1");
	        Random random = new Random();
			int num = random.nextInt(1500);
			if(num < 1500){
				throw new RuntimeException("throw get address exception!");
			}
			try {
				TimeUnit.MILLISECONDS.sleep(num);
			} catch (InterruptedException e) {}
        }
        
        System.out.println("======aaaaaaaaaaaaaaaaa");
        return new Address("China", "Beijing", "Beijing", "100# jianguo rd, Haidian district");
    }
    
    public Address adressFallBack(String customerId){
		System.out.println("-----fallback: get address failed!");
		return null;
	}
}
