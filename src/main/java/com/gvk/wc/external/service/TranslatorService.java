package com.gvk.wc.external.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "Home", url = "${translator.base-url}")
public interface TranslatorService {
	
	@GetMapping(value = "/translate/{word}")
	public String translate(@PathVariable("word") String word);

}