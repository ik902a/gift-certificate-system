package com.epam.esm.response;

import org.springframework.hateoas.RepresentationModel;

import com.epam.esm.dto.TagDto;

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
public class TagResponse extends RepresentationModel<TagResponse> {
	private long id;
    private String name;
    
//	public TagResponse(long id, String name) {
//		super();
//		this.id = id;
//		this.name = name;
//	}
	
    public static TagResponse valueOf(TagDto tag) {
    	return new TagResponse(tag.getId(), tag.getName());
    }

//	public long getId() {
//		return id;
//	}
//
//	public void setId(long id) {
//		this.id = id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
}
