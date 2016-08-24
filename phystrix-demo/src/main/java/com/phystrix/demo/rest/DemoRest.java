package com.phystrix.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.phystrix.demo.service.RemoteService;

@Controller
@RequestMapping(value = "/rest")
public class DemoRest {
    @Autowired
    private RemoteService remoteService;
    
    @RequestMapping(value="request",method = RequestMethod.GET)
    @ResponseBody
    public String quest()throws Exception {
    	return remoteService.requestRemote("aaaa", "b");
    }
}
