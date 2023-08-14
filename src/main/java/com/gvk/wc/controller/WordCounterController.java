package com.gvk.wc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gvk.wc.service.WordCountService;

@RequestMapping(path = "/word-counts")
@RestController
public class WordCounterController {

	private final WordCountService wordCountService;

	@Autowired
	public WordCounterController(WordCountService cakeService) {
		this.wordCountService = cakeService;
	}

	@GetMapping(value = "/{word}")
	@ResponseStatus(HttpStatus.OK)
	public Long getWordCount(@PathVariable(value = "word") String word) {
		return wordCountService.getWordCount(word);
	}

	@PostMapping(value = "/{word}")
	@ResponseStatus(HttpStatus.CREATED)
	public void countWord(@PathVariable(value = "word") String word) {
		wordCountService.count(word);
	}

}
