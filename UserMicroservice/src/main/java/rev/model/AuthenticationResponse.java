package rev.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthenticationResponse {

	private final String jwt;
	
	public String getJwt() {
        return jwt;
    }

}
