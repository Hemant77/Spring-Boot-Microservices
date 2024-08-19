package com.cric.project.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cric.project.model.MoM;
import com.cric.project.model.Squad;
import com.cric.project.model.TeamProfile;
import com.cric.project.model.TeamStatistic;
import com.cric.project.model.TotalMatches;
import com.cric.project.service.SquadService;
import com.cric.project.service.TeamProfileService;
import com.cric.project.service.TeamStatisticService;
import com.cric.project.utils.AccessUtil;
import com.cric.project.utils.ErrorHandlingUtil;
import com.cric.project.validator.SquadDuplicateValidator;
import com.cric.project.validator.SquadValidator;
import com.cric.project.validator.TeamProfileDuplicateValidator;
import com.cric.project.validator.TeamProfileValidator;
import com.cric.project.validator.TeamStatisticValidator;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.Valid;

/**
 * TeamManagementController is a REST controller that handles HTTP requests for
 * team profile, squad and team statistic
 * 
 * <p>
 * It provides REST end-points of creating, retrieving, updating team profile,
 * squad and team statistics
 * </p>
 * 
 * Please see the {@link TeamManagementController} class
 * 
 * @author Hemant Dhamal
 * @version 1.0
 */

@RestController
@CrossOrigin
public class TeamManagementController {

	/**
	 * Logger Factory initialization
	 */
	private final Logger logger = LoggerFactory.getLogger(TeamManagementController.class);

	/**
	 * Team profile duplication validator
	 */
	@Autowired
	TeamProfileDuplicateValidator teamProfileDuplicateValidator;

	/**
	 * Squad duplication validator
	 */
	@Autowired
	SquadDuplicateValidator squadDuplicateValidator;

	/**
	 * MessageSource service
	 */
	@Autowired
	private MessageSource messageSource;

	/**
	 * Team profile service
	 */
	@Autowired
	TeamProfileService teamProfileService;

	/**
	 * Squad service
	 */
	@Autowired
	SquadService squadService;

	/**
	 * Team statistic service
	 */
	@Autowired
	TeamStatisticService teamStatisticService;

	/**
	 * Create team profile
	 * 
	 * @param teamProfileValidator team profile details
	 * @param bindingResult
	 * @param model
	 * 
	 * @return Success or error message.
	 */
	@PostMapping("/teams")
	public ResponseEntity<String> createTeamProfile(@Valid @RequestBody TeamProfileValidator teamProfileValidator,
			BindingResult bindingResult, Model model) throws Exception {
		TeamProfile teamProfile = createTeamProfileObject(teamProfileValidator);
		teamProfileDuplicateValidator.validate(teamProfileValidator, bindingResult);
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(ErrorHandlingUtil.isAccessAllowed(bindingResult), HttpStatus.BAD_REQUEST);
		}
		String username = "user";
		String role = "role";
		if (!AccessUtil.isAccessAllowed(username, role)) {
			logger.warn(messageSource.getMessage("message.user.unautherised", null, LocaleContextHolder.getLocale()));
			return new ResponseEntity<>(
					messageSource.getMessage("message.user.unautherised", null, LocaleContextHolder.getLocale()),
					HttpStatus.UNAUTHORIZED);
		} else {
			teamProfileService.save(teamProfile);
			teamStatisticService.save(teamProfile);
			logger.info(messageSource.getMessage("message.teamProfile.createSuccessful", null,
					LocaleContextHolder.getLocale()));
			return new ResponseEntity<>(messageSource.getMessage("message.teamProfile.createSuccessful", null,
					LocaleContextHolder.getLocale()), HttpStatus.CREATED);
		}
	}

	/**
	 * Update team profile
	 * 
	 * @param teamProfileValidator team profile details
	 * @param bindingResult
	 * @param model
	 * 
	 * @return Success or error message.
	 */
	@PutMapping("/teams")
	public ResponseEntity<String> updateTeamProfile(@Valid @RequestBody TeamProfile teamProfile,
			BindingResult bindingResult, Model model) throws Exception {
		String username = "user";
		String role = "role";
		if (!AccessUtil.isAccessAllowed(username, role)) {
			logger.warn(messageSource.getMessage("message.user.unautherised", null, LocaleContextHolder.getLocale()));
			return new ResponseEntity<>(
					messageSource.getMessage("message.user.unautherised", null, LocaleContextHolder.getLocale()),
					HttpStatus.UNAUTHORIZED);
		} else {
			if (null != teamProfileService.update(teamProfile)) {
				logger.info(messageSource.getMessage("message.teamProfile.updateSuccessful", null,
						LocaleContextHolder.getLocale()));
				return new ResponseEntity<>(messageSource.getMessage("message.teamProfile.updateSuccessful", null,
						LocaleContextHolder.getLocale()), HttpStatus.OK);
			} else {
				logger.error(messageSource.getMessage("message.teamProfile.notFound", null,
						LocaleContextHolder.getLocale()));
				return new ResponseEntity<>(
						messageSource.getMessage("message.teamProfile.notFound", null, LocaleContextHolder.getLocale()),
						HttpStatus.NOT_FOUND);
			}
		}
	}

	/**
	 * Fetch team profile
	 * 
	 * @param teamName team name
	 * 
	 * @return team profile details
	 */
	@GetMapping("/teams/{teamName}")
	public ResponseEntity<String> getTeamProfile(@PathVariable String teamName) throws Exception {
		TeamProfile teamProfile = teamProfileService.findTeamProfileByName(teamName);
		if (null != teamProfile) {
			ObjectMapper objectMapper = new ObjectMapper();
			logger.info(messageSource.getMessage("message.teamProfile.fetchSuccessful", null,
					LocaleContextHolder.getLocale()));
			return new ResponseEntity<>(objectMapper.writeValueAsString(teamProfile), HttpStatus.OK);
		} else {
			logger.error(
					messageSource.getMessage("message.teamProfile.notFound", null, LocaleContextHolder.getLocale()));
			return new ResponseEntity<>(
					messageSource.getMessage("message.teamProfile.notFound", null, LocaleContextHolder.getLocale()),
					HttpStatus.NOT_FOUND);

		}
	}

	/**
	 * Create squad
	 * 
	 * @param squadValidator squad details
	 * @param bindingResult
	 * @param model
	 * 
	 * @return Success or error message.
	 */
	@PostMapping("/squads")
	public ResponseEntity<String> createSquad(@Valid @RequestBody SquadValidator squadValidator,
			BindingResult bindingResult, Model model) throws Exception {
		Squad squad = createSquadObject(squadValidator);
		squadDuplicateValidator.validate(squadValidator, bindingResult);
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(ErrorHandlingUtil.isAccessAllowed(bindingResult), HttpStatus.BAD_REQUEST);
		}
		String username = "user";
		String role = "role";
		if (!AccessUtil.isAccessAllowed(username, role)) {
			logger.warn(messageSource.getMessage("message.user.unautherised", null, LocaleContextHolder.getLocale()));
			return new ResponseEntity<>(
					messageSource.getMessage("message.user.unautherised", null, LocaleContextHolder.getLocale()),
					HttpStatus.UNAUTHORIZED);
		} else {
			squadService.save(squad);
			logger.info(
					messageSource.getMessage("message.squad.createSuccessful", null, LocaleContextHolder.getLocale()));
			return new ResponseEntity<>(
					messageSource.getMessage("message.squad.createSuccessful", null, LocaleContextHolder.getLocale()),
					HttpStatus.CREATED);
		}
	}

	/**
	 * Update squad
	 * 
	 * @param squadValidator squad details
	 * @param bindingResult
	 * @param model
	 * 
	 * @return Success or error message.
	 */
	@PutMapping("/squads")
	public ResponseEntity<String> updateSquad(@Valid @RequestBody SquadValidator squadValidator,
			BindingResult bindingResult, Model model) throws Exception {
		Squad squad = createSquadObject(squadValidator);
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(ErrorHandlingUtil.isAccessAllowed(bindingResult), HttpStatus.BAD_REQUEST);
		}
		String username = "user";
		String role = "role";
		if (!AccessUtil.isAccessAllowed(username, role)) {
			logger.warn(messageSource.getMessage("message.user.unautherised", null, LocaleContextHolder.getLocale()));
			return new ResponseEntity<>(
					messageSource.getMessage("message.user.unautherised", null, LocaleContextHolder.getLocale()),
					HttpStatus.UNAUTHORIZED);
		} else {
			if (null != squadService.update(squad)) {
				logger.info(messageSource.getMessage("message.squad.updateSuccessful", null,
						LocaleContextHolder.getLocale()));
				return new ResponseEntity<>(messageSource.getMessage("message.squad.updateSuccessful", null,
						LocaleContextHolder.getLocale()), HttpStatus.OK);
			} else {
				logger.error(messageSource.getMessage("message.squad.notFound", null, LocaleContextHolder.getLocale()));
				return new ResponseEntity<>(
						messageSource.getMessage("message.squad.notFound", null, LocaleContextHolder.getLocale()),
						HttpStatus.NOT_FOUND);
			}

		}
	}

	/**
	 * Fetch squad details
	 * 
	 * @param teamId team id
	 * @param teamId match id
	 * 
	 * @return squad details
	 */
	@GetMapping("/squads/{teamId}/{matchId}")
	public ResponseEntity<String> getSquadByTeamIdMatchId(@PathVariable Long teamId, @PathVariable Long matchId)
			throws Exception {
		Squad squad = squadService.findSquadByTeamIdMatchId(teamId, matchId);
		if (null != squad) {
			ObjectMapper objectMapper = new ObjectMapper();
			logger.info(
					messageSource.getMessage("message.squad.fetchSuccessful", null, LocaleContextHolder.getLocale()));
			return new ResponseEntity<>(objectMapper.writeValueAsString(squad), HttpStatus.OK);
		} else {
			logger.error(messageSource.getMessage("message.squad.notFound", null, LocaleContextHolder.getLocale()));
			return new ResponseEntity<>(
					messageSource.getMessage("message.squad.notFound", null, LocaleContextHolder.getLocale()),
					HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Update teamStatisticJson
	 * 
	 * @param teamStatisticJson team statistic details
	 * 
	 * @return Success or error message.
	 */
	@PutMapping("/teams/statistics")
	public ResponseEntity<String> updateTeamStatistic(@RequestBody Map<String, Object> teamStatisticJson,
			BindingResult bindingResult, Model model) throws Exception {
		String username = "user";
		String role = "role";
		if (!AccessUtil.isAccessAllowed(username, role)) {
			logger.warn(messageSource.getMessage("message.user.unautherised", null, LocaleContextHolder.getLocale()));
			return new ResponseEntity<>(
					messageSource.getMessage("message.user.unautherised", null, LocaleContextHolder.getLocale()),
					HttpStatus.UNAUTHORIZED);
		} else {
			if (bindingResult.hasErrors()) {
				return new ResponseEntity<>(ErrorHandlingUtil.isAccessAllowed(bindingResult), HttpStatus.BAD_REQUEST);
			}
			ObjectMapper mapper = new ObjectMapper();
			TeamStatisticValidator teamStatisticValidator = new TeamStatisticValidator(null,
					mapper.convertValue(teamStatisticJson.get("teamName"), String.class),
					mapper.convertValue(teamStatisticJson.get("captain"), String.class),
					mapper.convertValue(teamStatisticJson.get("vcaptain"), String.class),
					mapper.convertValue(teamStatisticJson.get("iccTitles"), Integer.class),
					mapper.convertValue(teamStatisticJson.get("totalMatches"), TotalMatches.class),
					mapper.convertValue(teamStatisticJson.get("mom"), MoM.class));
			TeamStatistic teamStatistic = createTeamStatistic(teamStatisticValidator);
			if (null != teamStatisticService.update(teamStatistic)) {
				logger.info(messageSource.getMessage("message.teamStatistic.updateSuccessful", null,
						LocaleContextHolder.getLocale()));
				return new ResponseEntity<>(messageSource.getMessage("message.teamStatistic.updateSuccessful", null,
						LocaleContextHolder.getLocale()), HttpStatus.OK);
			} else {
				logger.error(messageSource.getMessage("message.teamStatistic.updateSuccessful", null,
						LocaleContextHolder.getLocale()));
				return new ResponseEntity<>(messageSource.getMessage("message.teamStatistic.notFound", null,
						LocaleContextHolder.getLocale()), HttpStatus.NOT_FOUND);
			}
		}
	}

	/**
	 * Fetch statistics details
	 * 
	 * @param teamName team name
	 * 
	 * @return team statistic details
	 */
	@GetMapping("/teams/statistics/{teamName}")
	public ResponseEntity<String> getTeamStatByTeamName(@PathVariable String teamName) throws Exception {
		TeamStatistic teamStatistic = teamStatisticService.findTeamStatisticByName(teamName);
		if (teamStatistic != null) {
			ObjectMapper objectMapper = new ObjectMapper();
			logger.info(messageSource.getMessage("message.teamStatistic.fetchSuccessful", null,
					LocaleContextHolder.getLocale()));
			return new ResponseEntity<>(objectMapper.writeValueAsString(teamStatistic), HttpStatus.OK);
		} else
			logger.error(
					messageSource.getMessage("message.teamStatistic.notFound", null, LocaleContextHolder.getLocale()));
		return new ResponseEntity<>(
				messageSource.getMessage("message.teamStatistic.notFound", null, LocaleContextHolder.getLocale()),
				HttpStatus.NOT_FOUND);
	}

	/**
	 * @param teamProfileValidator This service convert TeamProfileValidator to
	 *                             TeamProfile object
	 * @return Team profile
	 */
	private TeamProfile createTeamProfileObject(TeamProfileValidator teamProfileValidator) {
		TeamProfile teamProfile = new TeamProfile();
		teamProfile.setTeamId(teamProfileValidator.getTeamId());
		teamProfile.setTeamName(teamProfileValidator.getTeamName());
		teamProfile.setHistory(teamProfileValidator.getHistory());
		return teamProfile;
	}

	/**
	 * @param squadValidator This service convert SquadValidator to Squad object
	 * @return Squad
	 */
	private Squad createSquadObject(SquadValidator squadValidator) {
		Squad squad = new Squad();
		squad.setSquadId(squadValidator.getSquadId());
		squad.setTeamId(squadValidator.getTeamId());
		squad.setMatchId(squadValidator.getMatchId());
		squad.setPlayers(squadValidator.getPlayers());
		return squad;
	}

	/**
	 * @param TeamStatisticValidator This service convert TeamStatisticValidator to
	 *                               Squad object
	 * @return TeamStatistic
	 */
	private TeamStatistic createTeamStatistic(TeamStatisticValidator teamStatisticValidator) {
		TeamStatistic teamStatistic = new TeamStatistic();
		teamStatistic.setId(teamStatisticValidator.getId());
		teamStatistic.setTeamName(teamStatisticValidator.getTeamName());
		teamStatistic.setCaptain(teamStatisticValidator.getCaptain());
		teamStatistic.setVcaptain(teamStatisticValidator.getVcaptain());
		teamStatistic.setIccTitles(teamStatisticValidator.getIccTitles());
		teamStatistic.setTotalMatches(teamStatisticValidator.getTotalMatches());
		teamStatistic.setMom(teamStatisticValidator.getMom());
		return teamStatistic;
	}
}
