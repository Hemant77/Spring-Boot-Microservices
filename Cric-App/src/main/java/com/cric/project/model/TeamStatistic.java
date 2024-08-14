package com.cric.project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

/**
 * TeamStatistic class
 * 
 * TeamStatistic class is database table structure
 * 
 * @author Hemant Dhamal
 * @version 1.0
 */
@Entity
@Table(name = "teamstatistic")
public class TeamStatistic {

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@NotNull
	@Column(name = "teamname", unique = true)
	private String teamName;

	@Column(name = "captain", unique = true)
	private String captain;

	@Column(name = "vcaptain", unique = true)
	private String vcaptain;

	@Column(name = "iccTitles")
	private Integer iccTitles;

	@Embedded
	private TotalMatches totalMatches;

	@Embedded
	private MoM mom;

	/**
	 * No-argument constructor
	 */
	public TeamStatistic() {
		super();
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
	public TeamStatistic(Long id, @NotNull String teamName, String captain, String vcaptain, Integer iccTitles,
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
		return "id = " + id + ", teamName = " + teamName + ", captain = " + captain + ", vcaptain = " + vcaptain
				+ ", iccTitles = " + iccTitles + ", totalMatches = " + totalMatches + ", mom = " + mom;
	}

}
