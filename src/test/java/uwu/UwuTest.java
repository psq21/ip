package uwu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UwuTest {
    @Test
    void getResponse_unknownCommand_returnsUnknownMessage() {
        assertEquals("eeek?? nani ??/", Uwu.getResponse("what is this"));
    }

    @Test
    void getResponse_bye_returnsExitMessage() {
        assertEquals("gwooooooooddbyyeee seeeeee youuuuuuuu <3", Uwu.getResponse("bye"));
    }

    @Test
    void actionFromInput_requiresExactMatchForListAndSort() {
        assertEquals(Uwu.Action.UNKNOWN, Uwu.Action.fromInput("listing"));
        assertEquals(Uwu.Action.UNKNOWN, Uwu.Action.fromInput("sort tasks"));
    }
}
