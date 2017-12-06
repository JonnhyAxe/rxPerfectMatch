package com.perfectmatch.persistence.model;


import java.util.Objects;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
/**
 *
 * Represents an match between two samples
 *
 */


public final class Match {


	@JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;
    private String name;
    private String sampleFromRule;
    private String rule;
  

	public Match() {
		super();
	}
	
	public Match(ObjectId id, String name, String sampleFromRule, String rule) {
		super();
		this.id = id;
		this.name = name;
		this.sampleFromRule = sampleFromRule;
		this.rule = rule;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSampleFromRule() {
		return sampleFromRule;
	}

	public void setSampleFromRule(String sampleFromRule) {
		this.sampleFromRule = sampleFromRule;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	/*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {

        return Objects.hash(id, rule, name, sampleFromRule);
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
        		Objects.equals(name, other.name) && 
        		Objects.equals(sampleFromRule, other.sampleFromRule);

    }

}
