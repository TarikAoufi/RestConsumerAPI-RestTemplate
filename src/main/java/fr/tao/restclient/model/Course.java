package fr.tao.restclient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Course {
	
	private Long id;
	private String title;
	private String description;
	private boolean animated;
	
	public Course(String title, String description, boolean animated) {
		super();
		this.title = title;
		this.description = description;
		this.animated = animated;
	}

}
