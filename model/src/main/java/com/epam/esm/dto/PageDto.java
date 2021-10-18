package com.epam.esm.dto;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The {@code PageDto} class is implementation of pattern DTO for transmission page
 * entity between service and controller.
 *
 * @param <T> the entity which presents on page
 * @author Ihar Klepcha
 */
public class PageDto<T> extends RepresentationModel<TagDto> {
	private List<T> content;
    private long totalPages;
    private long pageNumber;
    @JsonIgnore
    private long offset;
    @JsonIgnore
    private long limit;
    
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
	public PageDto(List<T> content, long totalPages, long pageNumber, long offset, long limit) {
		super();
		this.content = content;
		this.totalPages = totalPages;
		this.pageNumber = pageNumber;
		this.offset = offset;
		this.limit = limit;
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

	public long getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(long page) {
		this.pageNumber = page;
	}

	public long getOffset() {
		return offset;
	}

	public void setOffset(long offset) {
		this.offset = offset;
	}

	public long getLimit() {
		return limit;
	}

	public void setLimit(long limit) {
		this.limit = limit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + (int) (limit ^ (limit >>> 32));
		result = prime * result + (int) (offset ^ (offset >>> 32));
		result = prime * result + (int) (pageNumber ^ (pageNumber >>> 32));
		result = prime * result + (int) (totalPages ^ (totalPages >>> 32));
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
		PageDto other = (PageDto) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (limit != other.limit)
			return false;
		if (offset != other.offset)
			return false;
		if (pageNumber != other.pageNumber)
			return false;
		if (totalPages != other.totalPages)
			return false;
		return true;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("\nPage DTO{ content=").append(content);
		sb.append(", totalPages=").append(totalPages);
		sb.append(", pageNumber=").append(pageNumber);
		sb.append(", offset=").append(offset);
		sb.append(", limit=").append(limit).append(" }");
		return sb.toString();
	} 
}
