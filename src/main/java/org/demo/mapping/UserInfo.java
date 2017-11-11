package org.demo.mapping;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "userinfo", uniqueConstraints = {@UniqueConstraint(columnNames = "username")})
public class UserInfo {

	private long userinfoid;
	private String username;
	private String userloc;
	private String password;
	private String salt;
	private String pwdhash;

	public UserInfo() {
		super();
	}

	public UserInfo(String username, String userloc, String password, String salt, String pwdhash) {
		this.username = username;
		this.userloc = userloc;
		this.password = password;
		this.salt = salt;
		this.pwdhash = pwdhash;
	}

	@Id
	@Column(name = "userinfoid")
	
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userinfo_seq")
	@SequenceGenerator(name = "userinfo_seq", sequenceName = "userinfo_userinfoid_seq",allocationSize=1)

	//@GeneratedValue()
	public long getUserinfoid() {
		return userinfoid;
	}
	public void setUserinfoid(long userinfoid) {
		this.userinfoid = userinfoid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserloc() {
		return userloc;
	}
	public void setUserloc(String userloc) {
		this.userloc = userloc;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getPwdhash() {
		return pwdhash;
	}

	public void setPwdhash(String pwdhash) {
		this.pwdhash = pwdhash;
	}

	@Override
	public String toString() {
		return "Userinfo [userinfoid=" + userinfoid + ", username=" + username + ", userloc=" + userloc + "]";
	}
}