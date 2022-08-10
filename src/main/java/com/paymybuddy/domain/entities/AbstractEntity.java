package com.paymybuddy.domain.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Id;

@MappedSuperclass
public abstract class AbstractEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long Id;
	
	public Long getId() {
		return Id;
	    }

}
