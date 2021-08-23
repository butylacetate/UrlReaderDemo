package org.testtask.controller;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.testtask.exception.WrongDelimitersException;
import org.testtask.exception.WrongUrlException;
import org.testtask.service.WordsCountService;

import static org.junit.Assert.*;

public class InputControllerTest {

    private InputController inputController;

    @Before
    public void before() {
        inputController = new InputController(Mockito.mock(WordsCountService.class));
    }

    @Test
    public void testWrongUrl() {
        WrongUrlException exception = assertThrows(WrongUrlException.class, () -> inputController.getWordsCount("htp://someurl.ru/", ""));
        assertEquals("Wrong URL address: htp://someurl.ru/", exception.getMessage());
        exception = assertThrows(WrongUrlException.class, () -> inputController.getWordsCount("http:/someurl.ru", ""));
        assertEquals("Wrong URL address: http:/someurl.ru", exception.getMessage());
    }

    @Test
    public void testRightUrl() throws WrongDelimitersException, WrongUrlException, IOException {
        inputController.getWordsCount("https://test.ru", "{' ', ','}");
    }

    @Test
    public void testWrongDelimiters() {
        WrongDelimitersException exception = assertThrows(WrongDelimitersException.class, () -> inputController.getWordsCount("https://test.ru", "' ', ','"));
        assertEquals("Wrong delimiters format: ' ', ','", exception.getMessage());
        exception = assertThrows(WrongDelimitersException.class, () -> inputController.getWordsCount("https://test.ru", "{' ', ','"));
        assertEquals("Wrong delimiters format: {' ', ','", exception.getMessage());
        exception = assertThrows(WrongDelimitersException.class, () -> inputController.getWordsCount("https://test.ru", "' ', ','}"));
        assertEquals("Wrong delimiters format: ' ', ','}", exception.getMessage());
        exception = assertThrows(WrongDelimitersException.class, () -> inputController.getWordsCount("https://test.ru", "' , ','"));
        assertEquals("Wrong delimiters format: ' , ','", exception.getMessage());
    }

    @Test
    public void testRightDelimiter() throws WrongDelimitersException, WrongUrlException, IOException {
        inputController.getWordsCount("https://test.ru", "{' ', ',', '.', '! ', '?','\"', ';', ':', '[', ']', '(', ')', '\\n', '\\r', '\\t'}");
    }
}