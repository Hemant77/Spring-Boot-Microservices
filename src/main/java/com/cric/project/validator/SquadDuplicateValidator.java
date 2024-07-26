package com.cric.project.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.cric.project.model.Squad;
import com.cric.project.service.SquadService;

/**
 * SquadDuplicate Validator
 * 
 * SquadDuplicateValidator class is for handling duplicate squad entry error
 * 
 * @author Hemant Dhamal
 * @version 1.0
 *
 */
@Component
public class SquadDuplicateValidator implements Validator {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private SquadService squadService;

	@Override
	public boolean supports(Class<?> clazz) {
		return SquadValidator.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		SquadValidator squadValidator = (SquadValidator) target;
		Squad squad = squadService.findSquadByTeamIdMatchId(squadValidator.getTeamId(), squadValidator.getMatchId());
		if (null != squad) {
			errors.rejectValue("squadId", "", messageSource.getMessage("validation.squad.duplicate", null, null));
		}

	}

}
