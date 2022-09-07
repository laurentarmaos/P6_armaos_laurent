package com.paymybuddy.domain.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable 
public class FriendIdentity implements Serializable{
	
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "user_id_friend")
	private Long userIdFriend;

	
	
	public FriendIdentity() {
	}



	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public Long getUserIdFriend() {
		return userIdFriend;
	}

	public void setUserIdFriend(Long userIdFriend) {
		this.userIdFriend = userIdFriend;
	}
	
}
