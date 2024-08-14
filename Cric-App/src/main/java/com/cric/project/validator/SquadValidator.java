package com.cric.project.validator;

import java.util.Objects;

/**
 * Squad Validator
 * 
 * @author Hemant Dhamal
 * @version 1.0
 *
 */
public class SquadValidator {

	/**
	 * Squad id.
	 */
	private Long squadId;

	/**
	 * Team id.
	 */
	private Long teamId;

	/**
	 * Match id.
	 */
	private Long matchId;

	/**
	 * Player id's
	 */
	private Integer[] players;

	/**
	 * Default constructor.
	 */
	public SquadValidator() {
		super();

	}

	/**
	 * Constructor with arguments.
	 * 
	 * @param teamId  Team id
	 * @param matchId Match id
	 */
	public SquadValidator(Long squadId, Long teamId, Long matchId, Integer[] players) {
		super();
		this.squadId = squadId;
		this.teamId = teamId;
		this.matchId = matchId;
		this.players = players;
	}

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
	 * hash code.
	 * 
	 */
	@Override
	public int hashCode() {
		return Objects.hash(squadId, teamId, matchId, players);
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
		SquadValidator validator = (SquadValidator) object;
		return Objects.equals(teamId, validator.teamId) && Objects.equals(matchId, validator.matchId);
	}

}