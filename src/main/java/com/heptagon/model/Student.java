package com.heptagon.model;



import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Student 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int sid;
	private String studentName;
	private String collegeName;
	private String studentCode;
}
