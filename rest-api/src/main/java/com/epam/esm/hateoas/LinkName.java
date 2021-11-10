package com.epam.esm.hateoas;

/**
 * The {@code LinkName} class describes all link name and parameters
 * 
 * @author Ihar Klepcha
 */
public class LinkName {
	public static final String NEXT = "next";
	public static final String PREV = "prev";
    //link param 
	public static final String OFFSET = "offset";
	public static final String LIMIT = "limit";
    
	private LinkName() {
	}
}
