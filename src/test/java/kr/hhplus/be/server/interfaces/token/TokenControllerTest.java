package kr.hhplus.be.server.interfaces.token;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.hhplus.be.server.application.token.facade.TokenFacade;
import kr.hhplus.be.server.application.token.port.in.TokenCommand;
import kr.hhplus.be.server.application.token.port.out.TokenInfo;
import kr.hhplus.be.server.interfaces.token.controller.TokenController;

@WebMvcTest(controllers = {TokenController.class})
public class TokenControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockitoBean
	private TokenFacade tokenFacade;

	@Test
	public void createTest() throws Exception {
		// given
		TokenCommand tokenCommand = new TokenCommand(1L);
		TokenInfo tokenInfo = new TokenInfo("88LMpDHgHsdIa8Vx1JJ1xjMBME7vk0eH/dRJ9VMmuLgfkhFaQoYBupISOgbb0Rjj");

		// when
		when(tokenFacade.createToken(tokenCommand)).thenReturn(tokenInfo);

		// then
		mockMvc.perform(post("/token/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(tokenCommand)))
			.andDo(print())
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.userId").value(tokenInfo.userId()));
	}

}
