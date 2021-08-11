package rev.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Table(name="group")
public class Group {

	
	@Id
	@Column(name="group_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int groupId;
	
	@Column(name= "group_name", nullable= false, unique= true)
	private String groupName;

	@ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name = "user_id_fk",nullable = false)
	private User createdById;
	
	@ManyToMany(cascade= CascadeType.ALL, fetch= FetchType.EAGER )
	@JoinColumn(name = "user_id_fk",nullable = false, unique= true)
	private List<User> memberId = new ArrayList<>();

	public Group(int groupId, String groupName) {
		super();
		this.groupId = groupId;
		this.groupName = groupName;
	
	}

	
	
	
	
	
	
//	@JoinColumn(name = "user_id_fk",nullable = false)
//	@OneToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
//	private int groupAdmin;
	
//	private String groupSummary;
//	
//	private int createdAtTime;
	
 
}
