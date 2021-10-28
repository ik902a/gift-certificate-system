package com.epam.esm.dto;

import java.util.List;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

/**
 * The {@code PageDto} class is implementation of pattern DTO for transmission page
 * entity between service and controller.
 *
 * @param <T> the entity which presents on page
 * @see RepresentationModel
 * @author Ihar Klepcha
 */
public class PageDto<T> {
	private List<T> content;
    private long totalPages;
    private long pageNumber;
    private long offset;
    private long limit;
    
    /**
	 * Constructs a new Page DTO
	 */
	public PageDto() {
		super();
	}

	/**
	 * Constructs a new Page with the specified
	 * 
	 * @param content {@link List} of {@link T} list entities
	 * @param totalPages contains number total pages
	 * @param pageNumber contains number of page
	 * @param offset contains offset for pagination
	 * @param limit contains limit content for pagination
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
		return Objects.hash(content, limit, offset, pageNumber, totalPages);
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
		return Objects.equals(content, other.content) && limit == other.limit && offset == other.offset
				&& pageNumber == other.pageNumber && totalPages == other.totalPages;
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
