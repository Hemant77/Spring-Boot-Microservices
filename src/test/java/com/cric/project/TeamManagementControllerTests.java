package com.cric.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

import com.cric.project.controller.TeamManagementController;
import com.cric.project.model.TeamProfile;
import com.cric.project.service.SquadService;
import com.cric.project.service.TeamProfileService;
import com.cric.project.service.TeamStatisticService;
import com.cric.project.validator.SquadValidator;
import com.cric.project.validator.TeamProfileValidator;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This class has JUnit Test cases to test TeamManagementController REST API.
 * 
 * <p>
 * It tests REST end-points of creating, retrieving, updating team profile,
 * squad and team statistics
 * </p>
 * 
 * Please see the {@link TeamManagementControllerTests} class
 * 
 * @author Hemant Dhamal
 * @version 1.0
 */
@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@WebAppConfiguration
public class TeamManagementControllerTests {

	/**
	 * Mock Mvc
	 */
	private MockMvc mockMvc;

	/**
	 * Team Profile Service.
	 */
	@MockBean
	private TeamProfileService teamProfileService;

	/**
	 * Squad Service
	 */
	@MockBean
	SquadService squadService;

	/**
	 * TeamStatistic Service
	 */
	@MockBean
	TeamStatisticService teamStatisticService;

	/**
	 * Binding result
	 */
	@MockBean
	BindingResult bindingResult;

	/**
	 * Team profile validator.
	 */
	@MockBean
	TeamProfileValidator teamProfileValidator;

	/**
	 * Squad validator.
	 */
	@MockBean
	SquadValidator squadValidator;

	/**
	 * Mock TeamManagement controller.
	 */
	@InjectMocks
	private TeamManagementController teamManagementController;

	/**
	 * Message source
	 */
	@Autowired
	private MessageSource messageSource;

	private final Logger logger = LoggerFactory.getLogger(TeamManagementControllerTests.class);

	TeamProfile teamProfile1 = new TeamProfile(1L, "TeamA",
			"TeamA faced TeamB in the first ever Test, in 1877, and five years later the Tournament were born after TeamA's unlikely win at VenueA.");

	TeamProfile teamProfile2 = new TeamProfile(2L, "TeamC",
			"Teamc are renowned for punching above their weight and being resourceful despite being among the least-populated Test-playing nations.");

	TeamProfile teamProfile3 = new TeamProfile(3L, "TeamB", "TeamB faced TeamC in the Test, in 1977");

	TeamProfile teamProfile4 = new TeamProfile(4L, "TeamD", "TeamD faced TeamM in the Test, in 2000");

	TeamProfile teamProfile5 = new TeamProfile(4L, "TeamD", "TeamD faced TeamM in the Test, in 2000");

	TeamProfileValidator profileValidator1 = new TeamProfileValidator(1L, "TeamA",
			"TeamA faced TeamB in the first ever Test, in 1877, and five years later the Tournament were born after TeamA's unlikely win at VenueA.");

	/**
	 * set up mockMvc.
	 */
	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(teamManagementController).build();
	}

	/**
	 * Test get player REST end-point for success.
	 * 
	 * @throws Exception
	 */
	@Test
	public void getTeamProfileSuccess() throws Exception {
		when(teamProfileService.findTeamProfileByName(anyString())).thenReturn(teamProfile1);
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.get("/teams/TeamA").accept(MediaType.APPLICATION_JSON)).andReturn();
		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
		TeamProfileValidator result = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(),
				TeamProfileValidator.class);
		assertEquals(profileValidator1, result);
	}

//	/**
//	 * Test fetch team profile REST end-point for profile not found.
//	 * 
//	 * @throws Exception
//	 */
//	@Test
//	public void getTeamProfileNotFound() throws Exception {
//		when(teamProfileService.findTeamProfileByName("TeamZ")).thenReturn(null);
//		MvcResult mvcResult = mockMvc
//				.perform(MockMvcRequestBuilders.get("/teams/TeamZ").accept(MediaType.APPLICATION_JSON)).andReturn();
//		assertEquals(HttpStatus.NOT_FOUND.value(), mvcResult.getResponse().getStatus());
//		String result = mvcResult.getResponse().getContentAsString();
//		assertEquals(messageSource.getMessage("message.teamProfile.notFound", null, LocaleContextHolder.getLocale()),
//				"Team profile not found in the records");
//	}
}