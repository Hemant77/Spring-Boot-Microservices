package com.cric.project.service;

import com.cric.project.model.Squad;

/**
 * SquadService Interface
 * 
 * SquadService interface is to perform Squad operations
 * 
 * @author Hemant Dhamal
 * @version 1.0
 */
public interface SquadService {
	public Squad save(Squad squad);

	public Squad update(Squad squad);

	public Squad findSquadByTeamIdMatchId(Long teamId, Long matchId);
}
