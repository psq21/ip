package jukebox;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JukeboxTest {
    @Test
    void actionFromInput_recognisesCommands() {
        assertEquals(Jukebox.Action.BYE, Jukebox.Action.fromInput("bye"));
        assertEquals(Jukebox.Action.LIST, Jukebox.Action.fromInput("list"));
        assertEquals(Jukebox.Action.MARK, Jukebox.Action.fromInput("mark 1"));
        assertEquals(Jukebox.Action.UNMARK, Jukebox.Action.fromInput("unmark 1"));
        assertEquals(Jukebox.Action.TODO, Jukebox.Action.fromInput("todo task"));
        assertEquals(Jukebox.Action.DEADLINE, Jukebox.Action.fromInput("deadline task /by 2026-01-01"));
        assertEquals(Jukebox.Action.EVENT, Jukebox.Action.fromInput("event task /from 2026-01-01 /to 2026-01-02"));
        assertEquals(Jukebox.Action.DELETE, Jukebox.Action.fromInput("delete 1"));
    }

    @Test
    void actionFromInput_unknownCommand_returnsUnknown() {
        assertEquals(Jukebox.Action.UNKNOWN, Jukebox.Action.fromInput("wat"));
    }
}
