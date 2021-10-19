package com.epam.esm.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * The {@code TagDto} class is implementation of pattern DTO for transmission tag
 * entity between service and controller
 *
 * @author Ihar Klepcha
 * @see RepresentationModel
 */
public class TagDto {
	private long id;
	@NotBlank
    @Size(max = 45)
    private String name;
    
    /**
	 * Constructs a new Tag DTO
	 */
	public TagDto() {
		super();
	}

	/**
	 * Constructs a new tag DTO with the specified
	 * 
	 * @param tag id
	 * @param name {@link String} name
	 */
	public TagDto(long id, String name) {
		super();
		this.id = id;
		this.name = name;
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
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		TagDto other = (TagDto) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("\nTagDTO{ id=").append(id);
		sb.append(", name=").append(name).append(" }");
		return sb.toString();
	}
}
