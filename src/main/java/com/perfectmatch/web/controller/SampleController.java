package com.perfectmatch.web.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perfectmatch.persistence.model.Sample;
import com.perfectmatch.web.services.impl.SampleServiceBean;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/sample")
public class SampleController {

    @Autowired
    private SampleServiceBean SampleService;

	@GetMapping("/repo")
    public Flux<Sample> findByRepo() throws IOException {

        return SampleService.getDao().findAll();
    }

}
