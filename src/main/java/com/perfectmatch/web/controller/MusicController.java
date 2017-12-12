package com.perfectmatch.web.controller;


import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.perfectmatch.persistence.model.Music;
import com.perfectmatch.web.exception.MyBadRequestException;
import com.perfectmatch.web.services.impl.MusicServiceBean;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


/*
 * Controller of the Music Entity
 */


@RestController
@RequestMapping("/music")
public class MusicController {

	//TODO: change to MusicServiceBean
    @Autowired
    private MusicServiceBean musicService;

    @GetMapping("/repo")
    @Secured({ "ROLE_USER_READ" })
    public Flux<Music> findByRepo() throws IOException {

        return musicService.getDao().findAll();
    }

    @GetMapping("/{name}")
    public Flux<Music> findByName(@PathVariable("name")
    @Valid
    final String musicName) {

        return musicService.findByName(musicName);
    }

	@PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Music> create(@RequestBody
    @Valid
    final Music resource) {

        if (resource.getArtist() == null) {
            throw new MyBadRequestException("Artist must not be null");
        }
        return  musicService.getDao().save(resource);
    }

}
