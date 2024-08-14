package com.cric.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cric.project.model.Squad;

/**
 * SquadRepository Interface
 * 
 * SquadRepository Interface is to perform Squad CRUD operations
 * 
 * @author Hemant Dhamal
 * @version 1.0
 */
public interface SquadRepository extends JpaRepository<Squad, Long> {
	@Query("SELECT s FROM Squad s WHERE s.teamId= ?1 and s.matchId = ?2")
	Squad findSquadByTeamIdMatchId(Long teamId, Long matchId);
}
