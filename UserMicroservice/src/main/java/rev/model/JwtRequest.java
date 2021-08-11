package rev.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author Miguel and Eric
 * 
 * This is a model that is used to recieve login credentials as they are validated
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequest {
	private String username;
	private String password;
}
