package com.cric.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cric.project.model.TeamProfile;

/**
 * TeamProfileRepository Interface
 * 
 * TeamProfileRepository Interface is to perform team profile CRUD operations
 * 
 * @author Hemant Dhamal
 * @version 1.0
 */
public interface TeamProfileRepository extends JpaRepository<TeamProfile, Long> {
	@Query("SELECT t FROM TeamProfile t WHERE t.teamName = ?1")
	TeamProfile findTeamProfileByName(String teamName);

}
