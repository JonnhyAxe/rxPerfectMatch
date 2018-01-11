package com.perfectmatch.persistence.model;


import java.util.Objects;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.perfectmatch.common.model.NameableEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 *
 * Represents an match between two samples
 *
 */

//@Data
//@Document
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
public final class Match implements NameableEntity {


	@JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;
	private Object thisSample;
    private Object thatSampleFromRule;
    private String rule;
  
	public Match() {
		super();
	}

	public Match(ObjectId id, String thisSample, String thatSample, String rule) {
		super();
		this.id = id;
		this.thisSample = thisSample;
		this.thatSampleFromRule = thisSample;
		this.rule = rule;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	
	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public Object getThisSample() {
		return thisSample;
	}

	public void setThisSample(Object thisSample) {
		this.thisSample = thisSample;
	}

	public Object getThatSampleFromRule() {
		return thatSampleFromRule;
	}

	public void setThatSampleFromRule(Object thatSampleFromRule) {
		this.thatSampleFromRule = thatSampleFromRule;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {

        return Objects.hash(id, rule, thisSample, thatSampleFromRule);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Match other = (Match) obj;
        return id == other.id && 
        		Objects.equals(rule, other.rule) && 
        		Objects.equals(thisSample, other.thisSample) && 
        		Objects.equals(thatSampleFromRule, other.thatSampleFromRule);

    }




}
