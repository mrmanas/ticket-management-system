package com.gl.bean;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	   long id;
	String title;
	String description;
	String status;
}
