
package com.perfectmatch.web.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perfectmatch.persistence.model.Match;
import com.perfectmatch.web.services.impl.SampleMatchServiceBean;

@RestController
@RequestMapping("/match")
public class MatchController {

    @Autowired
    private SampleMatchServiceBean sampleMatchServiceBean;

    @GetMapping("/repo")
    public Iterable<Match> findByRepo() throws IOException {

        return sampleMatchServiceBean.findAll();
    }

}
