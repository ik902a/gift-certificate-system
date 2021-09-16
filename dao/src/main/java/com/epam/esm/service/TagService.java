package com.epam.esm.service;

import java.util.List;

import com.epam.esm.entity.Tag;

public interface TagService {
	
	  Tag create(Tag tag);
	  
	  List<Tag> findAll();
	  
	  Tag findById(long id);

	  void delete(long id);
}
