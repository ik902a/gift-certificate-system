package com.epam.esm.dto;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonFormat;

public class OrderDto extends RepresentationModel<OrderDto> {
	private long id;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	private ZonedDateTime date;
	@NotNull
	@Digits(integer = 4, fraction = 2)
	private BigDecimal cost;
	@NotBlank
	@Positive
	private int number;
	
	public OrderDto() {
		super();
	}

	public OrderDto(long id, ZonedDateTime date, BigDecimal cost, int number) {
		super();
		this.id = id;
		this.date = date;
		this.cost = cost;
		this.number = number;
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

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cost == null) ? 0 : cost.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + number;
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
		OrderDto other = (OrderDto) obj;
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
		if (number != other.number)
			return false;
		return true;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("\nOrderDTO{ id=").append(id);
		sb.append(", date=").append(date);
		sb.append(", cost=").append(cost);
		sb.append(", number=").append(number).append(" }");
		return sb.toString();
	}
}
