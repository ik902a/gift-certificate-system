package com.epam.esm.response;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.RepresentationModel;

import com.epam.esm.dto.PageDto;
import com.epam.esm.dto.UserDto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper=false)
public class PageUserResponse extends RepresentationModel<PageUserResponse> {
	private List<UserResponse> content;
    private long totalPages;
    private long pageNumber;
    
//	public PageUserResponse() {
//		super();
//	}
//
//	public PageUserResponse(List<UserResponse> content, long totalPages, long pageNumber) {
//		super();
//		this.content = content;
//		this.totalPages = totalPages;
//		this.pageNumber = pageNumber;
//	}
	
    public static PageUserResponse valueOf(PageDto<UserDto> page) {
    	List<UserResponse> contentResponse = page.getContent()
    			.stream()
    			.map(user -> UserResponse.valueOf(user))
    			.collect(Collectors.toList());
    	return new PageUserResponse(contentResponse, page.getTotalPages(), page.getPageNumber());
	}

//	public List<UserResponse> getContent() {
//		return content;
//	}
//
//	public void setContent(List<UserResponse> content) {
//		this.content = content;
//	}
//
//	public long getTotalPages() {
//		return totalPages;
//	}
//
//	public void setTotalPages(long totalPages) {
//		this.totalPages = totalPages;
//	}
//
//	public long getPageNumber() {
//		return pageNumber;
//	}
//
//	public void setPageNumber(long pageNumber) {
//		this.pageNumber = pageNumber;
//	}
}
