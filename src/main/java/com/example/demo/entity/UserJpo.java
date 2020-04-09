package com.example.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name = "User")
@Table(name = "TB_USER")
@NoArgsConstructor
public class UserJpo {

	@Id
	@Column(name = "USER_ID")
	private String userId;

	@Column(name = "USER_PASSWORD")

	private String userPassword;

	@Column(name = "USER_NAME")
	private String userName;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "USER_ID")
	private List<HistoryJpo> historyJpos;

	public UserJpo(User user) {

		BeanUtils.copyProperties(user, this);

	}

	public User toModel() {
		
		User user = new User();
		BeanUtils.copyProperties(this, user);
		return user;
	}

}
