package com.phystrix.controller;

import com.phystrix.common.Result;
import com.phystrix.model.Customer;
import com.phystrix.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/{customerId}", method = RequestMethod.GET)
    public Result<Customer> getCustomer(@PathVariable String customerId) {
        Customer customer = customerService.getCustomer(customerId);
        Result<Customer> result = new Result<Customer>();
        boolean success = customer==null? false : true;
        String messages = customer==null? "get customer failed" : "OK"; 
        result.setMessages(messages);
        result.setSuccess(success);
        result.setResult(customer);
        return result;
    }

}
