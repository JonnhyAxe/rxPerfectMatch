package com.perfectmatch.common.model;

import java.io.Serializable;

import com.perfectmatch.common.interfaces.ByIdQueryable;
import com.perfectmatch.common.interfaces.ByNameQueryable;

/**
 * <class description>
 *
 */
public interface NameableEntity extends ByIdQueryable, ByNameQueryable, Serializable {

}
