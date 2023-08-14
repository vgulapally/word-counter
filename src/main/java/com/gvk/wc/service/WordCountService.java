package com.gvk.wc.service;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public interface WordCountService {

	Long getWordCount(@NotEmpty(message = "Word should not be null") String word);

	void count(//
			@Pattern(regexp = "^[A-Za-z]*$", message = "Word should not contain special characters") //
			@NotEmpty(message = "Word should not be null") String word);

}
