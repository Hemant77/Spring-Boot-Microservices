package com.cric.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
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
import com.cric.project.model.MoM;
import com.cric.project.model.Squad;
import com.cric.project.model.TeamProfile;
import com.cric.project.model.TeamStatistic;
import com.cric.project.model.TotalMatches;
import com.cric.project.service.SquadService;
import com.cric.project.service.TeamProfileService;
import com.cric.project.service.TeamStatisticService;
import com.cric.project.validator.SquadValidator;
import com.cric.project.validator.TeamProfileDuplicateValidator;
import com.cric.project.validator.TeamProfileValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
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
	 * Team profile duplicate validator.
	 */
	@MockBean
	TeamProfileDuplicateValidator teamProfileDuplicateValidator;

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
	@MockBean
	private MessageSource messageSource;

	private final Logger logger = LoggerFactory.getLogger(TeamManagementControllerTests.class);

	TeamProfile teamProfile1 = new TeamProfile((long) 1, "TeamA",
			"TeamA faced TeamB in the first ever Test, in 1877, and five years later the Tournament were born after TeamA's unlikely win at VenueA.");

	TeamProfile teamProfile2 = new TeamProfile((long) 2, "TeamC",
			"Teamc are renowned for punching above their weight and being resourceful despite being among the least-populated Test-playing nations.");

	TeamProfile teamProfile3 = new TeamProfile(3L, "TeamB", "TeamB faced TeamC in the Test, in 1977");

	TeamProfile teamProfile4 = new TeamProfile(4L, "TeamD", "TeamD faced TeamM in the Test, in 2000");

	TeamProfile teamProfile5 = new TeamProfile(4L, "TeamD", "TeamD faced TeamM in the Test, in 2000");

	TeamProfileValidator profileValidator1 = new TeamProfileValidator((long) 1, "TeamA",
			"TeamA faced TeamB in the first ever Test, in 1877, and five years later the Tournament were born after TeamA's unlikely win at VenueA.");

	Integer[] players1 = { 1, 2, 3, 23, 5, 6, 7, 8, 9, 10, 11, 12, 24, 14, 15 };

	Squad squad1 = new Squad(1L, 1L, 1L, players1);

	SquadValidator squadValidator1 = new SquadValidator(1L, 1L, 1L, players1);

	TotalMatches totalMatches = new TotalMatches("t20", "TeamC", (long) 120);
	MoM moM = new MoM((long) 1, "PlayerA", 12);

	TeamStatistic teamStatistic1 = new TeamStatistic(1L, "TeamA", "PlayerA", "PlayerB", 5, totalMatches, moM);

	TeamStatistic teamStatistic2 = new TeamStatistic(1L, "TeamA", "PlayerA", "PlayerB", 5, totalMatches, moM);

	/**
	 * set up mockMvc.
	 */
	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(teamManagementController).build();
	}

	/**
	 * Test get team profile REST end-point for success.
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

	/**
	 * Test fetch team profile REST end-point for profile not found.
	 * 
	 * @throws Exception
	 */
	@Test
	public void getTeamProfileNotFound() throws Exception {
		when(teamProfileService.findTeamProfileByName("TeamZ")).thenReturn(null);
		when(messageSource.getMessage(any(), any(), any())).thenReturn("Team profile not found in the records");

		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.get("/teams/TeamZ").accept(MediaType.APPLICATION_JSON)).andReturn();
		assertEquals(HttpStatus.NOT_FOUND.value(), mvcResult.getResponse().getStatus());
		String result = mvcResult.getResponse().getContentAsString();

		assertEquals(messageSource.getMessage("message.teamProfile.notFound", null, LocaleContextHolder.getLocale()),
				result);
	}

	/**
	 * Team Profile REST end-point for success.
	 * 
	 * @throws Exception
	 * @throws JsonProcessingException
	 * 
	 */
	@Test
	public void createTeamProfileSuccess() throws JsonProcessingException, Exception {
		when(teamProfileService.save(teamProfile1)).thenReturn(teamProfile1);
		MvcResult mvcResult;
		mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.post("/teams").content(new ObjectMapper().writeValueAsString(profileValidator1))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andReturn();
		assertEquals(HttpStatus.CREATED.value(), mvcResult.getResponse().getStatus());
	}

	/**
	 * Test get squad REST end-point for success.
	 * 
	 * @throws Exception
	 */
	@Test
	public void getSquadSuccess() throws Exception {
		when(squadService.findSquadByTeamIdMatchId(any(), any())).thenReturn(squad1);
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.get("/squads/1/1").accept(MediaType.APPLICATION_JSON)).andReturn();
		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
		SquadValidator result = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(),
				SquadValidator.class);
		assertEquals(squadValidator1, result);
	}

	/**
	 * Test fetch squad REST end-point for profile not found.
	 * 
	 * @throws Exception
	 */
	@Test
	public void getSquadNotFound() throws Exception {
		when(squadService.findSquadByTeamIdMatchId((long) 2, (long) 2)).thenReturn(null);
		when(messageSource.getMessage(any(), any(), any())).thenReturn("Squad not found in the records");
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.get("/squads/2/2").accept(MediaType.APPLICATION_JSON)).andReturn();
		assertEquals(HttpStatus.NOT_FOUND.value(), mvcResult.getResponse().getStatus());
		String result = mvcResult.getResponse().getContentAsString();
		assertEquals(messageSource.getMessage("message.squad.notFound", null, LocaleContextHolder.getLocale()), result);
	}

	/**
	 * Test get team statistic REST end-point for success.
	 * 
	 * @throws Exception
	 */
	@Test
	public void getTeamStatisticSuccess() throws Exception {
		when(teamStatisticService.findTeamStatisticByName(anyString())).thenReturn(teamStatistic1);
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.get("/teams/statistics/TeamA").accept(MediaType.APPLICATION_JSON))
				.andReturn();
		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
		TeamStatistic result = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(),
				TeamStatistic.class);
		assertEquals(teamStatistic2, result);
	}

}