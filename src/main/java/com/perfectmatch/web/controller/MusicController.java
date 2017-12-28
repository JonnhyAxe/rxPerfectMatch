package com.perfectmatch.web.controller;


import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.perfectmatch.persistence.model.Music;
import com.perfectmatch.web.exception.MyBadRequestException;
import com.perfectmatch.web.services.impl.MusicServiceBean;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;
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
    public Mono<ResponseEntity<List<Music>>> findByRepo() throws IOException {
    	return musicService.getDao().findAll().collectList()
    			.map(music -> ok(music))
    			.defaultIfEmpty(noContent().build());

    }

    @GetMapping("/{name}")
    public Flux<Music> findByName(@PathVariable("name")
    @Valid
    final String musicName) {

        return musicService.findByName(musicName);
    }

	@PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public  Mono<ResponseEntity<?>> create(@RequestBody
    @Valid
    final Music resource) {

		return Mono.justOrEmpty(resource.getName())
				.flatMap(name -> musicService.findByName(name).blockFirst())
				.defaultIfEmpty(Boolean.FALSE)
				.flatMap(exists -> {

					if (exists) {
						throw new CustomerServiceException(HttpStatus.BAD_REQUEST,
							"Music already exists, to update an existing customer use PUT instead.");
					}
					return musicService.getDao().save(resource).map(saved -> {
						return created(URI.create(format("/music/%s", saved.getId()))).build();
					});
				});
		
        if (resource.getArtist() == null) {
            throw new MyBadRequestException("Artist must not be null");
        }
        
    }

}
