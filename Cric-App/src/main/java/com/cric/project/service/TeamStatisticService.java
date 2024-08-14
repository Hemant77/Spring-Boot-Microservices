package com.cric.project.service;

import com.cric.project.model.TeamProfile;
import com.cric.project.model.TeamStatistic;

/**
 * TeamStatisticService interface
 * 
 * TeamStatisticService interface is to perform team statistic operations
 * 
 * @author Hemant Dhamal
 * @version 1.0
 */
public interface TeamStatisticService {

	public TeamStatistic save(TeamProfile teamProfile);

	public TeamStatistic update(TeamStatistic teamStatistic);

	public TeamStatistic findTeamStatisticByName(String teamName);

}
