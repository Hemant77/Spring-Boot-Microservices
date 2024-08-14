package com.cric.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cric.project.model.TeamStatistic;

/**
 * TeamStatisticRepository Interface
 * 
 * TeamStatisticRepository Interface is to perform Team statistic CRUD
 * operations
 * 
 * @author Hemant Dhamal
 * @version 1.0
 */
public interface TeamStatisticRepository extends JpaRepository<TeamStatistic, Long> {
	@Query("SELECT t FROM TeamStatistic t WHERE t.teamName= ?1")
	TeamStatistic findTeamStatByTeamName(String teamName);

}
