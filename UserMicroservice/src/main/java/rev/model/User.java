package rev.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sm_user")
public class User {
	
	@Id
    @Column(name="sm_user_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int userId;
	

}
