/**
 * 
 */
package com.cric.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cric.project.model.TeamProfile;
import com.cric.project.repository.TeamProfileRepository;

/**
 * TeamProfileServiceImpl class
 * 
 * TeamProfileServiceImpl class is implementation class for TeamProfileService
 * interface
 * 
 * @author Hemant Dhamal
 * @version 1.0
 */
@Service
public class TeamProfileServiceImpl implements TeamProfileService {

	@Autowired
	TeamProfileRepository teamProfileRepository;

	@Override
	public TeamProfile save(TeamProfile teamProfile) {
		return teamProfileRepository.save(teamProfile);
	}

	@Override
	public TeamProfile update(TeamProfile teamProfile) {
		TeamProfile existingTeamProfile = teamProfileRepository.findTeamProfileByName(teamProfile.getTeamName());
		if (null != existingTeamProfile) {
			teamProfile.setTeamId(existingTeamProfile.getTeamId());
			teamProfileRepository.save(teamProfile);
			return teamProfile;
		}
		return existingTeamProfile;
	}

	@Override
	public TeamProfile findTeamProfileByName(String teamName) {
		return teamProfileRepository.findTeamProfileByName(teamName);
	}

}
