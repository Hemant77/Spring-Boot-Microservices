package com.cric.project.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * TotalMatches class
 * 
 * TotalMatches class is Embeddable class configure in TeamStatistic class
 * 
 * @author Hemant Dhamal
 * @version 1.0
 */
@Data
//@AllArgsConstructor
@Embeddable
public class TotalMatches {
	private String format;
	private String opponentTeam;
	private Long count;

	/**
	 * No-argument constructor
	 */
	public TotalMatches() {
		super();
	}

	/**
	 * Parameterized constructor
	 * 
	 * @param format
	 * @param opponentTeam
	 * @param count
	 */
	public TotalMatches(String format, String opponentTeam, Long count) {
		super();
		this.format = format;
		this.opponentTeam = opponentTeam;
		this.count = count;
	}

	/**
	 * Getter and setter methods for all the class variables
	 */
	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getOpponentTeam() {
		return opponentTeam;
	}

	public void setOpponentTeam(String opponentTeam) {
		this.opponentTeam = opponentTeam;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	/**
	 * @return TotalMatches object in String type
	 */
	@Override
	public String toString() {
		return "format = " + format + ", opponentTeam=" + opponentTeam + ", count=" + count;
	}

}
