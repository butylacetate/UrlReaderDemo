package org.testtask.service.impl;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.testtask.dao.WordsCountDAO;
import org.testtask.service.WordsCountParserService;
import org.testtask.service.WordsCountService;

public class WordsCountServiceImpl implements WordsCountService {
    private static final Logger logger = Logger.getLogger(WordsCountServiceImpl.class);

    private final WordsCountParserService wordsCountParserService;
    private final WordsCountDAO wordsCountDAO;

    public WordsCountServiceImpl(WordsCountParserService wordsCountParserService, WordsCountDAO wordsCountDAO) {
        this.wordsCountParserService = wordsCountParserService;
        this.wordsCountDAO = wordsCountDAO;
    }

    @Override
    public Map<String, Integer> computeWordsCount(String url, String delimiterPattern) throws IOException {
        logger.info("Compute words count");
        Document doc = Jsoup.connect(url).get();

        Map<String, Integer> wordsCount = wordsCountParserService.parseWordsCount(doc, delimiterPattern);
        wordsCountDAO.upsert(wordsCount);
        return wordsCount;
    }
}
