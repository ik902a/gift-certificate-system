package com.epam.esm.response;

import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.epam.esm.dto.TagDto;

/**
 * The {@code TagResponse} class describes the response tag
 * 
 * @author Ihar Klepcha
 * @see RepresentationModel
 */
public class TagResponse extends RepresentationModel<TagResponse> {
	private long id;
    private String name;
    
    /**
	 * Constructs a new response
	 */
	public TagResponse() {
		super();
	}

	/**
	 * Constructs a new response with the specified
	 * 
	 * @param id is tag id
	 * @param name {@link String} name
	 */
	public TagResponse(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	/**
	 * Builds a new response 
	 * 
	 * @param tag {@link TagDto}  entity
	 * @return {@link TagResponse} response
	 */
    public static TagResponse valueOf(TagDto tag) {
    	return new TagResponse(tag.getId(), tag.getName());
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(id, name);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		TagResponse other = (TagResponse) obj;
		return id == other.id && Objects.equals(name, other.name);
	}
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("\nTagResponse{ id=").append(id);
		sb.append(", name=").append(name).append(" }");
		return sb.toString();
	}  
}
