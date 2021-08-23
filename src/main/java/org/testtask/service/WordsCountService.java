package org.testtask.service;

import java.io.IOException;
import java.util.Map;

public interface WordsCountService {

    Map<String, Integer> computeWordsCount(String url, String delimiterPattern) throws IOException;
}
