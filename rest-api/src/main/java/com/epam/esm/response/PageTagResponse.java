package com.epam.esm.response;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.RepresentationModel;

import com.epam.esm.dto.PageDto;
import com.epam.esm.dto.TagDto;

public class PageTagResponse extends RepresentationModel<PageTagResponse> {
	private List<TagResponse> content;
    private long totalPages;
    private long pageNumber;
    
	public PageTagResponse(List<TagResponse> content, long totalPages, long pageNumber) {
		super();
		this.content = content;
		this.totalPages = totalPages;
		this.pageNumber = pageNumber;
	}
	
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
}
