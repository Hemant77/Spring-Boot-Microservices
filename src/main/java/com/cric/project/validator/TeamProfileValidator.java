package com.cric.project.validator;

import java.util.Objects;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * TeamProfile Duplicate Validator
 * 
 * @author Hemant Dhamal
 * @version 1.0
 *
 */
public class TeamProfileValidator {

	/**
	 * Team's id.
	 */
	private Long teamId;

	/**
	 * Team's name.
	 */
	@NotNull(message = "{validation.teamName.notNull}")
	@Size(min = 3, max = 30, message = "{validation.teamName.size}")
	private String teamName;

	private String history;

	/**
	 * Default constructor.
	 */
	public TeamProfileValidator() {

	}

	/**
	 * Constructor with arguments.
	 * 
	 * @param teamId   Team id
	 * @param teamName Team name
	 */
	public TeamProfileValidator(Long teamId, @Size(min = 3, max = 30, message = "{validation.size}") String teamName,
			String history) {
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
		return "TeamProfileValidator [teamId=" + teamId + ", teamName=" + teamName + ", history=" + history + "]";
	}

	/**
	 * hash code.
	 * 
	 */
	@Override
	public int hashCode() {
		return Objects.hash(teamId, teamName, history);
	}

	/**
	 * equals method.
	 */
	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (object == null)
			return false;
		if (getClass() != object.getClass())
			return false;
		TeamProfileValidator validator = (TeamProfileValidator) object;
		return Objects.equals(teamId, validator.teamId) && Objects.equals(teamName, validator.teamName);
	}

}