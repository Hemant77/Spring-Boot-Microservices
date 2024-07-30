package com.cric.project.validator;

import java.util.Objects;

import com.cric.project.model.MoM;
import com.cric.project.model.TotalMatches;

/**
 * TeamStatisticValidator class
 * 
 * TeamStatistic validator class is database table structure
 * 
 * @author Hemant Dhamal
 * @version 1.0
 */
public class TeamStatisticValidator {

	/**
	 * Record id
	 */
	private Long id;

	/**
	 * Team name
	 */
	private String teamName;

	/**
	 * Captain name
	 */
	private String captain;

	/**
	 * Vice captain name
	 */
	private String vcaptain;

	/**
	 * Total ICC titles
	 */
	private Integer iccTitles;

	/**
	 * Total matches played by team
	 */
	private TotalMatches totalMatches;

	/**
	 * Total Man of the Match receives by team players
	 */
	private MoM mom;

	/**
	 * No-argument constructor
	 */
	public TeamStatisticValidator() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Parameterized constructor
	 * 
	 * @param id
	 * @param teamName
	 * @param captain
	 * @param vcaptain
	 * @param iccTitles
	 * @param totalMatches
	 * @param mom
	 */
	public TeamStatisticValidator(Long id, String teamName, String captain, String vcaptain, Integer iccTitles,
			TotalMatches totalMatches, MoM mom) {
		super();
		this.id = id;
		this.teamName = teamName;
		this.captain = captain;
		this.vcaptain = vcaptain;
		this.iccTitles = iccTitles;
		this.totalMatches = totalMatches;
		this.mom = mom;
	}

	/**
	 * Getter and setter methods for all the class variables
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getCaptain() {
		return captain;
	}

	public void setCaptain(String captain) {
		this.captain = captain;
	}

	public String getVcaptain() {
		return vcaptain;
	}

	public void setVcaptain(String vcaptain) {
		this.vcaptain = vcaptain;
	}

	public Integer getIccTitles() {
		return iccTitles;
	}

	public void setIccTitles(Integer iccTitles) {
		this.iccTitles = iccTitles;
	}

	public TotalMatches getTotalMatches() {
		return totalMatches;
	}

	public void setTotalMatches(TotalMatches totalMatches) {
		this.totalMatches = totalMatches;
	}

	public MoM getMom() {
		return mom;
	}

	public void setMom(MoM mom) {
		this.mom = mom;
	}

	/**
	 * @return TeamStatistic object in String type
	 */
	@Override
	public String toString() {
		return "TeamStatisticValidator [id=" + id + ", teamName=" + teamName + ", captain=" + captain + ", vcaptain="
				+ vcaptain + ", iccTitles=" + iccTitles + ", totalMatches=" + totalMatches + ", mom=" + mom + "]";
	}

	/**
	 * Hashcode method
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id, teamName, captain, vcaptain, iccTitles, totalMatches, mom);
	}

	/**
	 * Equals method
	 */
	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (object == null)
			return false;
		if (getClass() != object.getClass())
			return false;
		TeamStatisticValidator teamStatisticValidator = (TeamStatisticValidator) object;
		return Objects.equals(id, teamStatisticValidator.id)
				&& Objects.equals(teamName, teamStatisticValidator.teamName)
				&& Objects.equals(captain, teamStatisticValidator.captain)
				&& Objects.equals(vcaptain, teamStatisticValidator.vcaptain)
				&& Objects.equals(iccTitles, teamStatisticValidator.iccTitles);

	}

}
