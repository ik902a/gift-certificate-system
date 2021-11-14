package com.epam.esm.response;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.hateoas.RepresentationModel;

import com.epam.esm.dto.PageDto;
import com.epam.esm.dto.TagDto;

/**
 * The {@code PageTagResponse} class describes the response page with tags
 * 
 * @author Ihar Klepcha
 * @see RepresentationModel
 */
public class PageTagResponse extends RepresentationModel<PageTagResponse> {
	private List<TagResponse> content;
	private long totalPages;
	private long pageNumber;

	/**
	 * Constructs a new page response
	 */
	public PageTagResponse() {
		super();
	}

	/**
	 * Constructs a new page response with the specified
	 * 
	 * @param content    {@link List} of {@link TagResponse} list entities
	 * @param totalPages contains number total pages
	 * @param pageNumber contains number of page
	 */
	public PageTagResponse(List<TagResponse> content, long totalPages, long pageNumber) {
		super();
		this.content = content;
		this.totalPages = totalPages;
		this.pageNumber = pageNumber;
	}

	/**
	 * Builds a new response
	 * 
	 * @param page {@link PageDto} of {@link TagDto} entities
	 * @return {@link PageTagResponse} response
	 */
	public static PageTagResponse valueOf(PageDto<TagDto> page) {
		List<TagResponse> contentResponse = page.getContent()
				.stream()
				.map(tag -> TagResponse.valueOf(tag))
				.collect(Collectors.toList());
		return new PageTagResponse(contentResponse, page.getTotalPages(), page.getPageNumber());
	}

	public List<TagResponse> getContent() {
		return content;
	}

	public void setContent(List<TagResponse> content) {
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

	public void setPageNumber(long pageNumber) {
		this.pageNumber = pageNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(content, pageNumber, totalPages);
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
		PageTagResponse other = (PageTagResponse) obj;
		return Objects.equals(content, other.content) && pageNumber == other.pageNumber
				&& totalPages == other.totalPages;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("Page response{ content=").append(content);
		sb.append(", totalPages=").append(totalPages);
		sb.append(", pageNumber=").append(pageNumber).append(" }");
		return sb.toString();
	}
}
