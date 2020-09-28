package com.healthcare.enrollment.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document("Dependent")
public class Dependent {

	@Id
	@NotNull
	private String id;
	
	private String matchId;
	
	@NotEmpty(message = "Please enter a name")
	private String name;
	
	private String birthDate;
	
	private Boolean isDeleted;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMatchId() {
		return matchId;
	}

	public void setMatchId(String matchId) {
		this.matchId = matchId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Dependent(@NotNull String id, String matchId, @NotEmpty(message = "Please enter a name") String name,
			String birthDate, Boolean isDeleted) {
		super();
		this.id = id;
		this.matchId = matchId;
		this.name = name;
		this.birthDate = birthDate;
		this.isDeleted = isDeleted;
	}

	public Dependent() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
