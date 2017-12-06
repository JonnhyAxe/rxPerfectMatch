package com.perfectmatch.common.interfaces;

import java.util.Set;

/**
 * <class description>
 *
 */
public interface ByArtistSearchable<T extends ByArtistQueryable> {

	
    Set<T> findByArtist(String name);
}
