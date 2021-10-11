package com.epam.esm.dto;

import java.util.List;

/**
 * The {@code PageDto} class is implementation of pattern DTO for transmission page
 * entity between service and controller.
 *
 * @param <T> the entity which presents on page
 * @author Ihar Klepcha
 */
public class PageDto<T> {
	private List<T> content;
    private long totalPosition;
    
    /**
	 * Constructs a new Page DTO
	 */
	public PageDto() {
		super();
	}

	/**
	 * Constructs a new tag with the specified
	 * 
	 * @param content {@link List} of {@link T} list entities
	 * @param totalPosition contains number total positions
	 */
	public PageDto(List<T> content, long totalPosition) {
		super();
		this.content = content;
		this.totalPosition = totalPosition;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public long getTotalPosition() {
		return totalPosition;
	}

	public void setTotalPosition(long totalPosition) {
		this.totalPosition = totalPosition;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + (int) (totalPosition ^ (totalPosition >>> 32));
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
		PageDto other = (PageDto) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (totalPosition != other.totalPosition)
			return false;
		return true;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("\nPage DTO{ content=").append(content);
		sb.append(", totalPosition=").append(totalPosition).append(" }");
		return sb.toString();
	} 
}
