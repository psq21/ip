package uwu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UwuTest {
    @Test
    void actionFromInput_recognisesCommands() {
        assertEquals(Uwu.Action.BYE, Uwu.Action.fromInput("bye"));
        assertEquals(Uwu.Action.LIST, Uwu.Action.fromInput("list"));
        assertEquals(Uwu.Action.MARK, Uwu.Action.fromInput("mark 1"));
        assertEquals(Uwu.Action.UNMARK, Uwu.Action.fromInput("unmark 1"));
        assertEquals(Uwu.Action.TODO, Uwu.Action.fromInput("todo task"));
        assertEquals(Uwu.Action.DEADLINE, Uwu.Action.fromInput("deadline task /by 2026-01-01"));
        assertEquals(Uwu.Action.EVENT, Uwu.Action.fromInput("event task /from 2026-01-01 /to 2026-01-02"));
        assertEquals(Uwu.Action.SORT, Uwu.Action.fromInput("sort"));
        assertEquals(Uwu.Action.DELETE, Uwu.Action.fromInput("delete 1"));
    }

    @Test
    void actionFromInput_unknownCommand_returnsUnknown() {
        assertEquals(Uwu.Action.UNKNOWN, Uwu.Action.fromInput("wat"));
    }
}
