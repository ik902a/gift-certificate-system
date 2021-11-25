package com.epam.esm.dto;

import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * The {@code TagDto} class is implementation of pattern DTO for transmission
 * tag entity between service and controller
 *
 * @author Ihar Klepcha
 */
public class TagDto {
	private Long id;
	@NotBlank
	@Size(max = 45)
	private String name;

	/**
	 * Constructs a tag DTO
	 */
	public TagDto() {
		super();
	}

	/**
	 * Constructs a tag DTO with the specified
	 * 
	 * @param id   {@link Long} tag id
	 * @param name {@link String} name
	 */
	public TagDto(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TagDto other = (TagDto) obj;
		return id == other.id && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("TagDTO{ id=").append(id);
		sb.append(", name=").append(name).append(" }");
		return sb.toString();
	}
}
