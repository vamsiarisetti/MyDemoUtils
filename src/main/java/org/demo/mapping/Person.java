package org.demo.mapping;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "persons")
public class Person implements Serializable {
	private long id;
	private String firstName;
	private String lastName;
	private String email;

	private UserInfo userInfo;

	@Id
	@Column(name = "person_id")
	//@GeneratedValue
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "persons_seq")
	@SequenceGenerator(name = "persons_seq", sequenceName = "persons_person_id_seq",allocationSize=1)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "userinfoid")
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	@Override
	public String toString() {
		 return "Person [id=" + id + ", firstName=" + firstName + ", lastName="
	                + lastName + ", email=" + email + "]";
	}
}