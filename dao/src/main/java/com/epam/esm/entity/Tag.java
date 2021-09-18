package com.epam.esm.entity;

/**
 * The {@code Tag} class describes the entity tag
 * 
 * @author Ihar Klepcha
 * @see Entity
 */
public class Tag extends Entity {
	private static final long serialVersionUID = 1L;
	private long id;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tag other = (Tag) obj;
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
		sb.append("\nTag{ ID = ").append(id);
		sb.append(", name = ").append(name).append(" }");
		return sb.toString();
	}    
}
