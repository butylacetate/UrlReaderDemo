package org.testtask.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.testtask.service.WordsCountParserService;

public class WordsCountParserServiceImpl implements WordsCountParserService {
    private static final Logger logger = Logger.getLogger(WordsCountParserServiceImpl.class);

    private final HashMap<String, Integer> wordsCount = new HashMap<>();

    @Override
    public Map<String, Integer> parseWordsCount(Document document, String splitPattern) {
        logger.info("Parse page started");
        wordsCount.clear();
        findTextNode(document.childNodes(), splitPattern);
        return wordsCount;
    }

    private void findTextNode(List<Node> nodes, String splitPattern) {
        nodes.forEach(node -> {
            if (node.nodeName().equals("#text")) {
                String text = ((TextNode) node).text().trim();
                if (!text.isEmpty()) {
                    addWords(text.split(splitPattern));
                }
            }
            if (!node.childNodes().isEmpty()) {
                findTextNode(node.childNodes(), splitPattern);
            }
        });
    }

    private void addWords(String ... words) {
        Arrays.stream(words).filter(word -> !word.isEmpty()).forEach(this::addWord);
    }

    private void addWord(String word) {
        Integer count = wordsCount.get(word);

        if (count != null) {
            wordsCount.put(word, ++count);
        } else {
            wordsCount.put(word, 1);
        }
    }
}
