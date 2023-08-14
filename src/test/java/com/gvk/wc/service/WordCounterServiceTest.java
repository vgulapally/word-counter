package com.gvk.wc.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.gvk.wc.external.service.TranslatorService;

@ExtendWith(MockitoExtension.class)
class WordCounterServiceTest {

	@Mock
	private TranslatorService translatorService;

	@InjectMocks
	private DefaultWordCountService wordCountService;

	@Test
	public void should_succeed_and_count_words_if_word_is_valid() throws Exception {
		when(translatorService.translate(any())).thenReturn("wordOne");
		wordCountService.count("wordOne");
		assertEquals(1, wordCountService.getWordCount("wordOne"));
	}
	
	@Test
	public void givenOneThreadIsWriting_whenAnotherThreadsWritesAtSameKey_AndGetCorrectValue() throws Exception {
		when(translatorService.translate(any())).thenReturn("wordConCurrent");
	    ExecutorService threadExecutor = Executors.newFixedThreadPool(10);

	    IntStream.range(0, 10).forEach((n) -> 
	    	threadExecutor.execute(() -> wordCountService.count("wordConCurrent")));

	    Thread.sleep(500);
	    assertEquals(10, wordCountService.getWordCount("wordConCurrent"));

	    if (threadExecutor.awaitTermination(2, TimeUnit.SECONDS)) {
	        threadExecutor.shutdown();
	    }
	}

	@Test
	public void should_succeed_and_get_correct_count_of_words_if_exists() throws Exception {
		when(translatorService.translate(any())).thenReturn("wordTwo");
		// 2 times triggered wordcount
		wordCountService.count("wordTwo");
		wordCountService.count("wordTwo");

		assertEquals(2, wordCountService.getWordCount("wordTwo"));
	}

	@Test
	public void should_succeed_and_return_zero_count_of_words_if_not_exists() throws Exception {
		assertEquals(0, wordCountService.getWordCount("wordThree"));
	}

}
