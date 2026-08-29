package jukebox;

import org.junit.jupiter.api.Test;

import static jukebox.Parser.*;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void parseIndex_empty() {
        String test = "mark";
        assertNull(parseIndex(test));

        test = "mark             ";
        assertNull(parseIndex(test));

        test = "unmark";
        assertNull(parseIndex(test));

        test = "delete ";
        assertNull(parseIndex(test));
    }

    @Test
    public void parseIndex_invalidInput() {
        String test = "delete 1.33";
        assertNull(parseIndex(test));

        test = "unmark true";
        assertNull(parseIndex(test));

        test = "mark somerandom words";
        assertNull(parseIndex(test));
    }

    @Test
    public void parseIndex_success() {
        String test = "mark 1";
        assertEquals(1, parseIndex(test));

        test = "delete 100000";
        assertEquals(100000, parseIndex(test));
    }

    @Test
    public void parseTodo_empty() {
        String test = "todo";
        assertNull(parseIndex(test));

        test = "todo ";
        assertNull(parseIndex(test));
    }

    @Test
    public void parseTodo_success() {
        String test = "todo hellohello";
        assertEquals("hellohello", parseTodo(test));

        test = "todo 2000-10-10";
        assertEquals("2000-10-10", parseTodo(test));

        test = "todo 2000-10-10           ";
        assertEquals("2000-10-10", parseTodo(test));
    }

    @Test
    public void parseDeadline_empty() {
        String test = "deadline";
        assertNull(parseDeadline(test));

        test = "deadline      ";
        assertNull(parseDeadline(test));
    }

    @Test
    public void parseDeadline_invalidInput() {
        String test = "deadline tasktask 1010-10-10";
        assertNull(parseDeadline(test));

        test = "deadline tasktask";
        assertNull(parseDeadline(test));
    }

    @Test
    public void parseDeadline_success() {
        String test = "deadline tasktask /by 1010-10-10";
        assertArrayEquals(new String[]{"tasktask", "1010-10-10"},
                parseDeadline(test));

        test = "deadline task        /by 1010-10-19";
        assertArrayEquals(new String[]{"task", "1010-10-19"},
                parseDeadline(test));

        test = "deadline task1 /by str";
        assertArrayEquals(new String[]{"task1", "str"},
                parseDeadline(test));
    }

    @Test
    public void parseEvent_empty() {
        String test = "event";
        assertNull(parseEvent(test));

        test = "      event ";
        assertNull(parseEvent(test));
    }

    @Test
    public void parseEvent_invalidInput() {
        String test = "event e";
        assertNull(parseEvent(test));

        test = "event e 1 ";
        assertNull(parseEvent(test));

        test = "event e /from 9999";
        assertNull(parseEvent(test));

        test = "event e /to 233";
        assertNull(parseEvent(test));
    }

    @Test
    public void parseEvent_success() {
        String test = "event math class /from 1010-10-10 /to 1001-10-10";
        assertArrayEquals(new String[] {"math class", "1010-10-10", "1001-10-10"},
                parseEvent(test));

        test = "event math /from 1010-10-10 /to 1001-10-10";
        assertArrayEquals(new String[] {"math", "1010-10-10", "1001-10-10"},
                parseEvent(test));
    }
}
