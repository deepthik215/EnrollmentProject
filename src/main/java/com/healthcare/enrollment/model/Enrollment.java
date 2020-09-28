package com.healthcare.enrollment.model;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document("Enrollment")
public class Enrollment {

	@Id
	@NotNull
	private String id;
	
	private String matchId;
	
	private String name;
	
	@NotNull
	private Boolean activationStatus;
	
	private Date birthDate;
	
	@NotBlank
	private String phoneNumber;
	
	@NotNull
	private List<Dependent> dependent;
	
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

	public Boolean getActivationStatus() {
		return activationStatus;
	}

	public void setActivationStatus(Boolean activationStatus) {
		this.activationStatus = activationStatus;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<Dependent> getDependent() {
		return dependent;
	}

	public void setDependent(List<Dependent> dependent) {
		this.dependent = dependent;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Enrollment(@NotNull String id, String matchId, String name, @NotNull Boolean activationStatus,
			Date birthDate, @NotBlank String phoneNumber, @NotNull List<Dependent> dependent, Boolean isDeleted) {
		super();
		this.id = id;
		this.matchId = matchId;
		this.name = name;
		this.activationStatus = activationStatus;
		this.birthDate = birthDate;
		this.phoneNumber = phoneNumber;
		this.dependent = dependent;
		this.isDeleted = isDeleted;
	}

	public Enrollment() {
		super();
		// TODO Auto-generated constructor stub
	}

}
