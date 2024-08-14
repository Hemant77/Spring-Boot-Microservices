package com.cric.project.service;

import com.cric.project.model.TeamProfile;

/**
 * TeamProfileService Interface
 * 
 * TeamProfileService interface is to perform team profile operations
 * 
 * @author Hemant Dhamal
 * @version 1.0
 */
public interface TeamProfileService {
	public TeamProfile save(TeamProfile teamProfile);

	public TeamProfile update(TeamProfile teamProfile);

	public TeamProfile findTeamProfileByName(String teamName);

}
