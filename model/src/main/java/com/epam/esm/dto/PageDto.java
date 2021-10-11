package com.epam.esm.dto;

import java.util.List;


/**
 * Class is implementation of pattern DTO for transmission page
 * entity between service and controller.
 *
 * @param <T> the entity which presents on page
 * @author Ihar Klepcha
 * @version 1.0
 */
public class PageDto<T> {
	private List<T> content;
    private long totalPages;
    
	public PageDto() {
		super();
	}

	public PageDto(List<T> content, long totalPages) {
		super();
		this.content = content;
		this.totalPages = totalPages;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public long getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(long totalPages) {
		this.totalPages = totalPages;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + (int) (totalPages ^ (totalPages >>> 32));
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
		if (totalPages != other.totalPages)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PageDto [content=" + content + ", totalPages=" + totalPages + "]";
	}
}
