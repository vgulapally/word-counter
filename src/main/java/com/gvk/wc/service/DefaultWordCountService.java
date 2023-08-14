package com.gvk.wc.service;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.gvk.wc.external.service.TranslatorService;


@Service
@Validated
public class DefaultWordCountService implements WordCountService {

	private final ConcurrentHashMap<String, Long> wordCounter;
	private final TranslatorService translatorService;

	public DefaultWordCountService(TranslatorService translatorService) {
		this.wordCounter = new ConcurrentHashMap<>();
		this.translatorService = translatorService;
	}

	@Override
	public Long getWordCount(String word) {
		return wordCounter.getOrDefault(word, 0L);
	}

	@Override
	public void count(String word) {
		String translatedWord = translatorService.translate(word);
		wordCounter.compute(translatedWord, (tokenKey, oldValue) -> oldValue == null ? 1 : oldValue + 1);
	}

}
