package com.cric.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cric.project.model.Squad;
import com.cric.project.repository.SquadRepository;

/**
 * SquadServiceImpl class
 * 
 * SquadServiceImpl class is implementation class for SquadService interface
 * 
 * @author Hemant Dhamal
 * @version 1.0
 */
@Service
public class SquadServiceImpl implements SquadService {

	@Autowired
	SquadRepository squadRepository;

	@Override
	public Squad save(Squad squad) {
		return squadRepository.save(squad);
	}

	@Override
	public Squad update(Squad squad) {
		Squad existingSquad = squadRepository.findSquadByTeamIdMatchId(squad.getTeamId(), squad.getMatchId());
		if (null != existingSquad) {
			squad.setSquadId(existingSquad.getSquadId());
			squadRepository.save(squad);
			return squad;
		}
		return existingSquad;
	}

	@Override
	public Squad findSquadByTeamIdMatchId(Long teamId, Long matchId) {
		return squadRepository.findSquadByTeamIdMatchId(teamId, matchId);
	}

}
