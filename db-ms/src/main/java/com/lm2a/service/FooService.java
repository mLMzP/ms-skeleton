package com.lm2a.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Service;

import com.lm2a.web.ApplicationNameController;

@Service
public class FooService {
	
	private static final Logger log = LoggerFactory.getLogger(FooService.class);
	
	@Autowired
	Tracer tracer;
	
	public void printLog() {
		log.info("Test log");
	}
	
	public void miLog() {
		Span miSpan = tracer.nextSpan().name("lm2a-custom.span");
		try(Tracer.SpanInScope ws = this.tracer.withSpan(miSpan.start())){
			try {
				Thread.sleep(500);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			log.info("Este es mi log");
		}finally {
			miSpan.end();
		}
	}

}
