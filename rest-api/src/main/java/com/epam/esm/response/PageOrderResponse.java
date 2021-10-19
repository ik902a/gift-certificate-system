package com.epam.esm.response;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.RepresentationModel;

import com.epam.esm.dto.OrderDto;
import com.epam.esm.dto.PageDto;

public class PageOrderResponse extends RepresentationModel<PageOrderResponse> {
	private List<OrderResponse> content;
    private long totalPages;
    private long pageNumber;
    
	public PageOrderResponse(List<OrderResponse> content, long totalPages, long pageNumber) {
		super();
		this.content = content;
		this.totalPages = totalPages;
		this.pageNumber = pageNumber;
	}

    public static PageOrderResponse valueOf(PageDto<OrderDto> page) {
    	List<OrderResponse> contentResponse = page.getContent()
    			.stream()
    			.map(order -> OrderResponse.valueOf(order))
    			.collect(Collectors.toList());
    	return new PageOrderResponse(contentResponse, page.getTotalPages(), page.getPageNumber());
	}

	public List<OrderResponse> getContent() {
		return content;
	}

	public void setContent(List<OrderResponse> content) {
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
