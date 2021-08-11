package com.revature.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="Users")
public class User {

	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	@Column(name="username", nullable = false, unique = true)
	private String username;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="middle_name")
	private String middleName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="password", nullable = false)
	private String password;
	
	@Column(name="profile_picture", nullable = false)
	@ColumnDefault(value="'Default.png'")
	private String profilePicture;
	
	@Column(name="background_picture", nullable = false)
	@ColumnDefault(value="'DefaultBackground.png'")
	private String backgroundPicture;
	
	@Column(name="email", nullable = false, unique = true)
	private String email;
}