package com.perfectmatch.web.controller;


import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perfectmatch.persistence.model.Music;
import com.perfectmatch.web.services.impl.MusicServiceBean;

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
   // @Secured({ "ROLE_USER_READ" })
    public Mono<ResponseEntity<List<Music>>> findByRepo() throws IOException {
    	return musicService.getDao().findAll().collectList()
    			.map(music -> ok(music));
    			//.defaultIfEmpty(noContent().build());

    }

    @GetMapping("/{name}")
    public Mono<ResponseEntity<Music>> findByName(@PathVariable("name")
    @Valid
    final String musicName) {

        return musicService.findByName(musicName)
        		.map(music -> ok(music))
    			.defaultIfEmpty(notFound().build());
    }
	
	@PostMapping()
//    @ResponseStatus(HttpStatus.CREATED)
    public  Mono<ResponseEntity<?>> create(@RequestBody @Valid final Music resource) throws Exception {
		
		 return Mono.justOrEmpty(resource.getName())
				.map(name -> {
					
					if (Objects.isNull(musicService.findByName(name).block())) {//TODO: is this pure reactive?
//						throw new CustomerException(HttpStatus.BAD_REQUEST,
//							"Customer already exists, to update an existing customer use PUT instead.");
						throw new RuntimeException("Music already exists, to update an existing Music");
					}
					return created(URI.create(String.format("/customers/%s", this.musicService.create(resource).block().getId()))).build();

				});
        
    }

}
