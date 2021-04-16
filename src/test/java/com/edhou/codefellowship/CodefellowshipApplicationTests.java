package com.edhou.codefellowship;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.ContentResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CodefellowshipApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	MockMvc mockMvc;

	public void testHomePage() throws Exception {
		mockMvc.perform(get("/"))
				.andExpect(content().string(containsString("Login")))
				.andExpect(content().string(containsString("Sign up")))
				.andExpect(status().isOk());
	}

	public void testSignUp() throws Exception {
		mockMvc.perform(get("/signup"))
				.andExpect(content().string(containsString("username")))
				.andExpect(content().string(containsString("password")))
				.andExpect(status().isOk());
	}
}
