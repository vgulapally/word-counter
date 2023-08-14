package com.gvk.wc.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.gvk.wc.external.service.TranslatorService;

@SpringBootTest
@AutoConfigureMockMvc
class WordCounterControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private TranslatorService translatorService;

	@Test
	public void post_request_should_succeed_and_count_words_if_word_is_valid() throws Exception {
		when(translatorService.translate(any())).thenReturn("wordOne");
		mvc.perform(post("/word-counts/wordOne")).andExpect(status().isCreated());
	}

	@Test
	public void post_request_should_return_400_and_fail_count_words_if_word_contains_numeric() throws Exception {
		when(translatorService.translate(any())).thenReturn("word123");
		mvc.perform(post("/word-counts/word123")).andExpect(status().isBadRequest());
	}
	
	@Test
	public void post_request_should_return_400_and_fail_count_words_if_word_contains_special_characters() throws Exception {
		when(translatorService.translate(any())).thenReturn("word$$$$");
		mvc.perform(post("/word-counts/word$$$$")).andExpect(status().isBadRequest());
	}

	@Test
	public void get_request_should_succeed_and_get_count_of_words_if_exists() throws Exception {
		when(translatorService.translate(any())).thenReturn("wordTwo");
		// 2 times triggered wordcount
		mvc.perform(post("/word-counts/wordTwo")).andExpect(status().isCreated());
		mvc.perform(post("/word-counts/wordTwo")).andExpect(status().isCreated());

		MvcResult mvcResult = mvc.perform(get("/word-counts/wordTwo")).andExpect(status().isOk()).andReturn();
		String responseAsString = mvcResult.getResponse().getContentAsString();
		assertEquals("2", responseAsString);
	}

	@Test
	public void get_request_should_succeed_and_get_count_of_words_if_not_exists() throws Exception {
		when(translatorService.translate(any())).thenReturn("wordThree");

		MvcResult mvcResult = mvc.perform(get("/word-counts/wordThree")).andExpect(status().isOk()).andReturn();
		String responseAsString = mvcResult.getResponse().getContentAsString();
		assertEquals("0", responseAsString);
	}

}
