package com.cric.project.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.cric.project.model.TeamProfile;
import com.cric.project.service.TeamProfileService;

/**
 * TeamProfileDuplicate Validator
 * 
 * SquadDuplicateValidator class is for handling duplicate squad entry error
 * 
 * @author Hemant Dhamal
 * @version 1.0
 *
 */
@Component
public class TeamProfileDuplicateValidator implements Validator {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private TeamProfileService teamProfileService;

	@Override
	public boolean supports(Class<?> clazz) {
		return TeamProfileValidator.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// Team's name should not be same
		TeamProfileValidator teamProfileValidator = (TeamProfileValidator) target;

		TeamProfile profile = teamProfileService.findTeamProfileByName(teamProfileValidator.getTeamName());

		if (null != profile) {
			errors.rejectValue("teamName", "",
					messageSource.getMessage("validation.teamProfile.duplicate", null, null));
		}

	}

}
