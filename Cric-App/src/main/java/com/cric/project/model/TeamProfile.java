package com.cric.project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

/**
 * TeamProfile class
 * 
 * TeamProfile class is database table structure
 * 
 * @author Hemant Dhamal
 * @version 1.0
 */
@Entity
@Table(name = "teamprofile")
public class TeamProfile {

	@Column(name = "teamId")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long teamId;

	@NotNull
	@Column(name = "teamName", unique = true)
	private String teamName;

	@Column(name = "history")
	@Lob
	private String history;

	/**
	 * No-argument constructor
	 */
	public TeamProfile() {
		super();
	}

	/**
	 * Parameterized constructor
	 * 
	 * @param teamId
	 * @param teamName
	 * @param history
	 */
	public TeamProfile(Long teamId, String teamName, String history) {
		super();
		this.teamId = teamId;
		this.teamName = teamName;
		this.history = history;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	@Override
	public String toString() {
		return "id = " + teamId + ", teamName = " + teamName + ", history = " + history;
	}

}
