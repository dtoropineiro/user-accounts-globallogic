package com.dario.useraccounts.model;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;

	private Long id;
	private Date created;
	private Date modified;
	private Date lastLogin;
	private Boolean isActive;
	private String token;
	public JwtResponse(String token) {
		this.token = token;
	}

}