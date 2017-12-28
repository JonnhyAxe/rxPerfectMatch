package com.perfectmatch.common.interfaces;

import java.io.Serializable;
import java.util.List;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IOperations<T extends Serializable> {

    // find - one

    //T findOne(final long id);

    /**
     * - contract: if nothing is found, an empty list will be returned to the
     * calling client <br>
     */
	Mono<List<T>> findAll();


    // create

    Mono<T> create(final T resource);

    // update

    void update(final T resource);

    // delete

    //void delete(final long id);

    void deleteAll();

    // count

    Mono<Long> count();

}
