package org.testtask.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.testtask.exception.WrongDelimitersException;
import org.testtask.exception.WrongUrlException;
import org.testtask.service.WordsCountService;

public class InputController {

    private final WordsCountService wordsCountService;

    private static final String URL_PATTERN = "^(https?)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

    public InputController(WordsCountService wordsCountService) {
        this.wordsCountService = wordsCountService;
    }

    public Map<String, Integer> getWordsCount(String url, String delimiters) throws WrongUrlException, WrongDelimitersException, IOException {
        if (!url.matches(URL_PATTERN)) {
            throw new WrongUrlException(url);
        }

        if (!delimiters.matches("\\{.*}")) {
            throw new WrongDelimitersException(delimiters);
        }

        List<String> delimiterList = Arrays.stream(delimiters.substring(1, delimiters.length() - 1).split(", ")).collect(Collectors.toList());
        if (!delimiterList.stream().allMatch(s -> s.matches("'.*'"))) {
            throw new WrongDelimitersException(delimiters);
        }
        String delimiterPattern = delimiterList.stream().map(s -> s.substring(1, s.length() - 1)).collect(Collectors.joining(",", "[", "]"));

        return wordsCountService.computeWordsCount(url, delimiterPattern);
    }
}
