package com.dario.useraccounts.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER")
public class User implements Serializable {

	private static final long serialVersionUID = 5926468583005150707L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "USERID")
	private Long userid;
	private String username;

	@Column(name = "PASSWORD")
	@NotNull
	@Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,15}$")
	private String password;

	@Column(name = "EMAIL")
	@NotNull
	@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
	private String email;

	@Column(name = "CREATED")
	private Date created;

	@Column(name = "MODIFIED")
	private Date modified;

	@Column(name = "LAST_LOGIN")
	private Date lastLogin;

	@Column(name = "TOKEN")
	private String token;

	@Column(name = "IS_ACTIVE")
	private Boolean isActive;

	@JsonManagedReference
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user",cascade= CascadeType.ALL)
	@Column(name = "PHONE")
	private List<Phone> phone;

	public User(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
	}


}