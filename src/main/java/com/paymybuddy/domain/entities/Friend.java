package com.paymybuddy.domain.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "friend")
public class Friend {
	
	@EmbeddedId
	private FriendIdentity friendIdentity;
	
	
	public Friend() {
		
	}


	public FriendIdentity getFriendIdentity() {
		return friendIdentity;
	}

	public void setFriendId(FriendIdentity friendIdentity) {
		this.friendIdentity = friendIdentity;
	}
	
	
	
}
