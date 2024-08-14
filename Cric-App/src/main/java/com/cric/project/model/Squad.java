package com.cric.project.model;

import java.util.Arrays;

import org.hibernate.annotations.Array;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

/**
 * Squad class
 * 
 * Squad class is database table structure
 * 
 * @author Hemant Dhamal
 * @version 1.0
 */
@Entity
@Table(name = "squad")
public class Squad {

	@Column(name = "squadId")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long squadId;

	@NotNull
	@Column(name = "teamId")
	private Long teamId;

	@NotNull
	@Column(name = "matchId")
	private Long matchId;

	@NotNull
	@Column(name = "players")
	@Array(length = 15)
	private Integer[] players;

	/**
	 * No-argument constructor
	 */
	public Squad() {
		super();
	}

	/**
	 * Parameterized constructor
	 * 
	 * @param squadId
	 * @param teamId
	 * @param matchId
	 * @param players
	 */
	public Squad(Long squadId, Long teamId, Long matchId, Integer[] players) {
		super();
		this.squadId = squadId;
		this.teamId = teamId;
		this.matchId = matchId;
		this.players = players;
	}

	/**
	 * Getter and setter methods for all the class variables
	 */
	public Long getSquadId() {
		return squadId;
	}

	public void setSquadId(Long squadId) {
		this.squadId = squadId;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public Long getMatchId() {
		return matchId;
	}

	public void setMatchId(Long matchId) {
		this.matchId = matchId;
	}

	public Integer[] getPlayers() {
		return players;
	}

	public void setPlayers(Integer[] players) {
		this.players = players;
	}

	/**
	 * @return Squad object in String type
	 */
	@Override
	public String toString() {
		return "squadId = " + squadId + ", teamId = " + teamId + ", matchId = " + matchId + ", players = "
				+ Arrays.toString(players);
	}

}
