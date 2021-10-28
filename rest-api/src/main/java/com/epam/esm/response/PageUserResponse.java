package com.epam.esm.response;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.hateoas.RepresentationModel;

import com.epam.esm.dto.PageDto;
import com.epam.esm.dto.UserDto;

/**
 * The {@code PageUserResponse} class describes the response page with users
 * 
 * @author Ihar Klepcha
 * @see RepresentationModel
 */
public class PageUserResponse extends RepresentationModel<PageUserResponse> {
	private List<UserResponse> content;
    private long totalPages;
    private long pageNumber;
    
    /**
	 * Constructs a new page response
	 */
	public PageUserResponse() {
		super();
	}

	/**
	 * Constructs a new page response with the specified
	 * 
	 * @param content {@link List} of {@link UserResponse} list entities
	 * @param totalPages contains number total pages
	 * @param pageNumber contains number of page
	 */
	public PageUserResponse(List<UserResponse> content, long totalPages, long pageNumber) {
		super();
		this.content = content;
		this.totalPages = totalPages;
		this.pageNumber = pageNumber;
	}
	
	/**
	 * Builds a new response 
	 * 
	 * @param page {@link PageDto} of {@link UserDto} list entities
	 * @return {@link PageUserResponse} response
	 */
    public static PageUserResponse valueOf(PageDto<UserDto> page) {
    	List<UserResponse> contentResponse = page.getContent()
    			.stream()
    			.map(user -> UserResponse.valueOf(user))
    			.collect(Collectors.toList());
    	return new PageUserResponse(contentResponse, page.getTotalPages(), page.getPageNumber());
	}

	public List<UserResponse> getContent() {
		return content;
	}

	public void setContent(List<UserResponse> content) {
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
		PageUserResponse other = (PageUserResponse) obj;
		return Objects.equals(content, other.content) && pageNumber == other.pageNumber
				&& totalPages == other.totalPages;
	}
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("\nPage DTO{ content=").append(content);
		sb.append(", totalPages=").append(totalPages);
		sb.append(", pageNumber=").append(pageNumber).append(" }");
		return sb.toString();
	} 
}
