
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

import com.perfectmatch.persistence.model.Match;
import com.perfectmatch.web.services.impl.SampleMatchServiceBean;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/match")
public class MatchController {

    @Autowired
    private SampleMatchServiceBean sampleMatchServiceBean;

    @GetMapping("/repo")
    public Mono<ResponseEntity<List<Match>>> findByRepo() throws IOException {
    	return sampleMatchServiceBean.findAll()
    			.map(match -> ok(match))
    			.defaultIfEmpty(noContent().build());
    }

}
