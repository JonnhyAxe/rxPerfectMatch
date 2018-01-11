package com.perfectmatch.web.controller;


import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

import java.io.IOException;
import java.net.URI;
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
   // @Secured({ "ROLE_USER_READ" })
    public Flux<Music> findByRepo() throws IOException {
    	return musicService.findAll();
    }

    @GetMapping("/{name}")
    public Mono<Music> findByName(@PathVariable("name") @Valid  final String musicName) {
        return musicService.findByName(musicName);
    }
	
	@PostMapping() //refactor return to Music
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
