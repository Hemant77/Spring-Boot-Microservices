package com.cric.project.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * MoM class
 * 
 * MoM class is Embeddable class configure in TeamStatistic class
 * 
 * @author Hemant Dhamal
 * @version 1.0
 */
@Data
//@AllArgsConstructor
@Embeddable
public class MoM {
	private Long playerId;
	private String playerName;
	private String highestMoM;

	/**
	 * No-argument constructor
	 */
	public MoM() {
		super();
	}

	/**
	 * Parameterized constructor
	 * 
	 * @param playerId
	 * @param playerName
	 * @param highestMoM
	 * 
	 */
	public MoM(Long playerId, String playerName, String highestMoM) {
		super();
		this.playerId = playerId;
		this.playerName = playerName;
		this.highestMoM = highestMoM;
	}

	/**
	 * Getter and setter methods for all the class variables
	 */
	public Long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getHighestMoM() {
		return highestMoM;
	}

	public void setHighestMoM(String highestMoM) {
		this.highestMoM = highestMoM;
	}

	/**
	 * @return MoM object in String type
	 */
	@Override
	public String toString() {
		return "playerId = " + playerId + ", playerName = " + playerName + ", highestMoM = " + highestMoM;
	}

}
