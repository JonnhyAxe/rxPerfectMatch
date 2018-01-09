package com.perfectmatch.web.controller;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perfectmatch.persistence.model.Sample;
import com.perfectmatch.web.services.impl.SampleServiceBean;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/sample")
public class SampleController {

    @Autowired
    private SampleServiceBean sampleService;

	@GetMapping("/repo")
    public Mono<ResponseEntity<List<Sample>>> findByRepo() throws IOException {
		//musicService.getDao().findAll().collectList()
		
		return sampleService.getDao().findAll().collectList()
    			.map(music -> ok(music))
    			.defaultIfEmpty(noContent().build());
    }
	
	

}
