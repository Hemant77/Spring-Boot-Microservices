package com.cric.project.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cric.project.model.TeamProfile;
import com.cric.project.model.TeamStatistic;
import com.cric.project.repository.TeamStatisticRepository;

/**
 * TeamStatisticServiceImpl class
 * 
 * TeamStatisticServiceImpl class is implementation class for
 * TeamStatisticService interface
 * 
 * @author Hemant Dhamal
 * @version 1.0
 */
@Service
public class TeamStatisticServiceImpl implements TeamStatisticService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	TeamStatisticRepository teamStatisticRepository;

	@Override
	public TeamStatistic save(TeamProfile teamProfile) {
		TeamStatistic teamStatistic = new TeamStatistic(null, teamProfile.getTeamName(), null, null, null, null, null);
		return teamStatisticRepository.save(teamStatistic);
	}

	@Override
	public TeamStatistic update(TeamStatistic teamStatistic) {
		TeamStatistic existingTeamStatistic = teamStatisticRepository
				.findTeamStatByTeamName(teamStatistic.getTeamName());
		if (null != existingTeamStatistic) {
			logger.info("existingTeamStatistic not null");
			teamStatistic.setId(existingTeamStatistic.getId());
			teamStatisticRepository.save(teamStatistic);
			return teamStatistic;
		}
		logger.info("existingTeamStatistic null");
		return existingTeamStatistic;
	}

	@Override
	public TeamStatistic findTeamStatisticByName(String teamName) {
		return teamStatisticRepository.findTeamStatByTeamName(teamName);
	}

}
