package com.epam.esm.response;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.RepresentationModel;

import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.dto.PageDto;

public class PageGiftCertificateResponse extends RepresentationModel<PageGiftCertificateResponse> {
	private List<GiftCertificateResponse> content;
    private long totalPages;
    private long pageNumber;
    
	public PageGiftCertificateResponse(List<GiftCertificateResponse> content, long totalPages, long pageNumber) {
		super();
		this.content = content;
		this.totalPages = totalPages;
		this.pageNumber = pageNumber;
	}

    public static PageGiftCertificateResponse valueOf(PageDto<GiftCertificateDto> page) {
    	List<GiftCertificateResponse> contentResponse = page.getContent()
    			.stream()
    			.map(giftCertificate -> GiftCertificateResponse.valueOf(giftCertificate))
    			.collect(Collectors.toList());
    	return new PageGiftCertificateResponse(contentResponse, page.getTotalPages(), page.getPageNumber());
	}

	public List<GiftCertificateResponse> getContent() {
		return content;
	}

	public void setContent(List<GiftCertificateResponse> content) {
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
