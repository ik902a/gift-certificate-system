package com.epam.esm.response;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.hateoas.RepresentationModel;

import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.dto.PageDto;

/**
 * The {@code PageGiftCertificateResponse} class describes the response page with certificates
 * 
 * @author Ihar Klepcha
 * @see RepresentationModel
 */
public class PageGiftCertificateResponse extends RepresentationModel<PageGiftCertificateResponse> {
	private List<GiftCertificateResponse> content;
    private long totalPages;
    private long pageNumber;
    
    /**
	 * Constructs a new page response
	 */
	public PageGiftCertificateResponse() {
		super();
	}

	/**
	 * Constructs a new page response with the specified
	 * 
	 * @param content {@link List} of {@link GiftCertificateResponse} list entities
	 * @param totalPages contains number total pages
	 * @param pageNumber contains number of page
	 */
	public PageGiftCertificateResponse(List<GiftCertificateResponse> content, long totalPages, long pageNumber) {
		super();
		this.content = content;
		this.totalPages = totalPages;
		this.pageNumber = pageNumber;
	}

	/**
	 * Builds a new response 
	 * 
	 * @param page {@link PageDto} of {@link GiftCertificateDto} list entities
	 * @return {@link PageGiftCertificateResponse} response
	 */
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
		PageGiftCertificateResponse other = (PageGiftCertificateResponse) obj;
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
