package rev.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Table(name="See_Firsts")
public class SeeFirst {
	
	@Id
	@Column(name="seefirst_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int seeFirstId;
	
	
	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="user_FK")
	private User initUser;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="otheruser_FK")
	private User otherUser;

	public SeeFirst(User initUser, User otherUser) {
		super();
		this.initUser = initUser;
		this.otherUser = otherUser;
	}
	
	public User reducedUser(User user) {
		user.setSeeFirst(null);
		user.setPassword(" ");
		return user;
		
	}
	
	public User getInitUser() {
		return reducedUser(initUser);
		
	}
	
	public User getOtherUser() {
		return reducedUser(otherUser);
		
	}

	
	
	
	
	
	

}
