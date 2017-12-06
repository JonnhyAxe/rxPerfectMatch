package com.perfectmatch.persistence.model;

import java.util.Set;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.perfectmatch.common.model.NameableEntity;

/**
 * This class represents an Sample of one specific music
 *
 */

public class Sample  implements NameableEntity {


	@JsonSerialize(using = ToStringSerializer.class)
	private ObjectId id;
    private String name;
    private int timestamp;
    private Set<Match> mathes;


    public Sample() {
		super();
	}
    
    public Sample(ObjectId id, String name, int timestamp, Set<Match> mathes) {
		super();
		this.id = id;
		this.name = name;
		this.timestamp = timestamp;
		this.mathes = mathes;
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

	public int getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
	}

	public Set<Match> getMathes() {
		return mathes;
	}

	public void setMathes(Set<Match> mathes) {
		this.mathes = mathes;
	}

	//TODO: Change these methods
	/*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + (id == null ? 0 : id.hashCode());
        result = prime * result + (mathes == null ? 0 : mathes.hashCode());
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + timestamp;
        return result;
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
        Sample other = (Sample) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        }
        else if (!id.equals(other.id)) {
            return false;
        }
        if (mathes == null) {
            if (other.mathes != null) {
                return false;
            }
        }
        else if (!mathes.equals(other.mathes)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        }
        else if (!name.equals(other.name)) {
            return false;
        }
        if (timestamp != other.timestamp) {
            return false;
        }
        return true;
    }


}
