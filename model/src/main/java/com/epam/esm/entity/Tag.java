package com.epam.esm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The {@code Tag} class describes the entity tag
 * 
 * @author Ihar Klepcha
 * @see AbstractEntity
 */
@Entity
@Table(name="tags")
public class Tag extends AbstractEntity {
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    
    /**
	 * Constructs a new tag
	 */
	public Tag() {
	}

	/**
	 * Constructs a new tag with the specified
	 * 
	 * @param id {@link long} tag id
	 * @param name {@link String} name
	 */
	public Tag(long id, String name) {
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
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("\nTag{ id=").append(id);
		sb.append(", name=").append(name).append(" }");
		return sb.toString();
	}    
}
