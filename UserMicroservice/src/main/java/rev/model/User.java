package rev.model;

import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
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
	
	@Column(name="first_name", nullable = false)
	private String firstName;
	
	@Column(name="middle_name")
	private String middleName;
	
	@Column(name="last_name", nullable = false)
	private String lastName;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(name="password", nullable = false)
	private String password;
	
	@ElementCollection
    @OrderColumn(name = "user_salt", nullable = false)
    private byte[] salt;
	
	@Column(name="profile_picture", nullable = false)
	@ColumnDefault(value="'Default.png'")
	private String profilePicture;
	
	@Column(name="background_picture", nullable = false)
	@ColumnDefault(value="'DefaultBackground.png'")
	private String backgroundPicture;
	
	@Column(name="email", nullable = false, unique = true)
	private String email;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(name="reset_token")
	private String resetToken;
	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="group_list")
	private List<Group> groupList = new ArrayList<>();
//	private List<User> groupList = new ArrayList<>();
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="createdByUser")
	private List<Group> myCreatedGroups = new ArrayList<>();

	@OneToMany(mappedBy = "initUser", fetch = FetchType.LAZY)
	private Set<SeeFirst> seeFirst = new HashSet<>();
	



	
	

	public User(String username, String firstName, String lastName, String password, String email) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
	}

	public User(int userId, String username, String firstName, String middleName, String lastName, String password,
			String profilePicture, String backgroundPicture, String email) {
		super();
		this.userId = userId;
		this.username = username;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.password = password;
		this.profilePicture = profilePicture;
		this.backgroundPicture = backgroundPicture;
		this.email = email;
	}
	
	
	public User(int userId, String username, String firstName, String middleName, String lastName, String password,
			byte[] salt, String profilePicture, String backgroundPicture, String email) {
		super();
		this.userId = userId;
		this.username = username;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.password = password;
		this.salt = salt;
		this.profilePicture = profilePicture;
		this.backgroundPicture = backgroundPicture;
		this.email = email;
	}

	

	public User(int userId, String username, String firstName, String middleName, String lastName, String password,
			byte[] salt, String profilePicture, String backgroundPicture, String email, String resetToken) {
		super();
		this.userId = userId;
		this.username = username;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.password = password;
		this.salt = salt;
		this.profilePicture = profilePicture;
		this.backgroundPicture = backgroundPicture;
		this.email = email;
		this.resetToken = resetToken;
	}
	
	
	
	public User(int userId) {
		super();
		this.userId = userId;
	}	
//	public User(String username2, String password2, Object object) {
//
//	}
	
	//@JsonIgnore
	public String getPassword() {
		return this.password;	
	}
	
	//@JsonIgnore
	public String getResetToken() {
		return this.resetToken;
	}
	
	@JsonIgnore
	public byte[] getSalt() {
		return salt;
	}






	
}

