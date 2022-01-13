package com.job.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.job.model.Hnb;

@Service
public class HnbService {
	
    private RestTemplate restTemplate;
    
	
	public HnbService(RestTemplate restTemplate) {
		super();
		this.restTemplate = restTemplate;
	}
    
	public String callWebService() {
		Hnb[] root = restTemplate.getForObject(
				  "https://api.hnb.hr/tecajn/v2?valuta=EUR",
				  Hnb[].class);
	    if(root!=null&&root.length>0)
	    	return root[0].getSrednjiTecaj();
	    else
	    	return null;
	
	}



}
