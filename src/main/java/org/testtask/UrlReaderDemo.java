package org.testtask;

import java.io.IOException;
import java.util.Map;

import org.testtask.controller.InputController;
import org.testtask.dao.WordsCountDAO;
import org.testtask.database.InitialDB;
import org.testtask.exception.WrongDelimitersException;
import org.testtask.exception.WrongUrlException;
import org.testtask.service.impl.WordsCountParserServiceImpl;
import org.testtask.service.impl.WordsCountServiceImpl;


public class UrlReaderDemo {

    public static void main(String[] args) throws IOException, WrongDelimitersException, WrongUrlException {
        if (args.length != 2) {
            System.err.println("You should write 2 arguments: URL, delimiters\n" +
                    "Example: \"https://www.simbirsoft.com/\" \"{' ', ',', '.', '! ', '?','\"', ';', ':', '[', ']', '(', ')', '\\n', '\\r', '\\t'}\"");
        }

        InitialDB initialDB = new InitialDB();
        initialDB.init();

        InputController inputController = new InputController(new WordsCountServiceImpl(new WordsCountParserServiceImpl(), new WordsCountDAO()));

        printResult(inputController.getWordsCount(args[0], args[1]));
    }

    private static void printResult(Map<String, Integer> result) {
        result.entrySet().forEach(entry -> System.out.printf("%s - %s%n", entry.getKey(), entry.getValue()));
    }
}
