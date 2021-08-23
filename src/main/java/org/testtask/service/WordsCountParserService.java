package org.testtask.service;

import java.util.Map;

import org.jsoup.nodes.Document;

public interface WordsCountParserService {

    Map<String, Integer> parseWordsCount(Document document, String splitPattern);
}
