package com.epam.esm.hateoas;

/**
 * The {@code LinkName} class describes all link name and parameters
 * 
 * @author Ihar Klepcha
 */
public class LinkName {
	public static final String DELETE = "delete";
	public static final String UPDATE = "update";
	public static final String NEXT = "next";
	public static final String PREV = "prev";
    //link param 
	public static final String OFFSET = "offset";
    
	private LinkName() {
	}
}
