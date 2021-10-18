package com.epam.esm.dto;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The {@code OrderForUserDto} class is implementation of pattern DTO for transmission order
 * entity in user between service and controller
 *
 * @author Ihar Klepcha
 * @see RepresentationModel
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class OrderForUserDto extends RepresentationModel<OrderForUserDto> {
	private long id;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	private ZonedDateTime date;
	private BigDecimal cost;

    /**
	 * Constructs a new Order for user DTO
	 */
	public OrderForUserDto() {
		super();
	}

	/**
	 * Constructs a new order for user DTO with the specified
	 * 
	 * @param id is order id
	 * @param date {@link ZonedDateTime} creating date
	 * @param cost {@link BigDecimal} cost order
	 */
	public OrderForUserDto(long id, ZonedDateTime date, BigDecimal cost) {
		super();
		this.id = id;
		this.date = date;
		this.cost = cost;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ZonedDateTime getDate() {
		return date;
	}

	public void setDate(ZonedDateTime date) {
		this.date = date;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cost == null) ? 0 : cost.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
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
		OrderForUserDto other = (OrderForUserDto) obj;
		if (cost == null) {
			if (other.cost != null)
				return false;
		} else if (!cost.equals(other.cost))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("\nOrderDTO{ id=").append(id);
		sb.append(", date=").append(date);
		sb.append(", cost=").append(cost).append(" }");
		return sb.toString();
	}
}
