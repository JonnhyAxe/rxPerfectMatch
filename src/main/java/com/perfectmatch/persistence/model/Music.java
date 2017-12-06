package com.perfectmatch.persistence.model;

import java.util.Objects;
import java.util.Set;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.perfectmatch.common.interfaces.ByArtistQueryable;
import com.perfectmatch.common.model.NameableEntity;


/**
 *
 * This class represents an Music to sample from
 *
 */

public final class Music implements NameableEntity, ByArtistQueryable {



	@JsonSerialize(using = ToStringSerializer.class)
	private ObjectId id;

    private String artist;

	private String name;

    private String style;

    private Set<Sample> samples;


    public Music() {
		super();

	}
    
    public Music(ObjectId id, String artist, String name, String style, Set<Sample> samples) {
		super();
		this.id = id;
		this.artist = artist;
		this.name = name;
		this.style = style;
		this.samples = samples;
	}
    
    @Override
	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}
	
    @Override
	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public Set<Sample> getSamples() {
		return samples;
	}

	public void setSamples(Set<Sample> samples) {
		this.samples = samples;
	}

	/*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {


        return Objects.hash(this.artist, this.id, this.name, this.samples);
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
        Music other = (Music) obj;
       
        return Objects.equals(id, other.id) && 
        		Objects.equals(artist, other.artist) && 
        		Objects.equals(name, other.name) && 
        		Objects.equals(style, other.style) &&
        		Objects.equals(samples, other.samples);
    }

	static public Builder ofType(Style musicStyle) {
		return new Builder(musicStyle);
	}

	static public Builder from(Music music) {

		final Builder builder = new Builder(Style.valueOf(music.style));
		builder.id = music.id;
		builder.artist = music.artist;
		builder.name =  music.name;
		builder.samples =  music.samples;
		
		return builder;
	}
	
    static public final class Builder {
    	private ObjectId id;
        private String artist;
    	private String name;
        private Style style;
        private Set<Sample> samples;
        
		public Builder(Style musicType) {
			if (musicType == null) {
				//TODO: change
				//throw new CustomerServiceException(BAD_REQUEST, "Customer type can not be null.");
				throw new IllegalArgumentException();
			}
			this.style = musicType;
		}
        
		public Builder withId(ObjectId id) {
			this.id = id;
			return this;
		}
		
		public Builder withArtist(String artist) {
			this.artist = artist;
			return this;
		}
		
		public Builder withName(String name) {
			this.name = name;
			return this;
		}
		
		public Builder withSamples(Set<Sample> samples) {
			this.samples = samples;
			return this;
		}
		
        public Music build() {
			return new Music(id, artist, name, style.name(), samples);
		}
    }

}
