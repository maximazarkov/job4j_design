package ru.job4j.io;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

public class ResultFileTest {
    @Test
    public void when2on2() {
        StringBuilder expect = new StringBuilder();
        final String LS = System.lineSeparator();
        expect.append(" | 1   2").append(LS);
        expect.append("-+------").append(LS);
        expect.append("1| 1   2   ").append(LS);
        expect.append("2| 2   4   ").append(LS);
        MatcherAssert.assertThat(expect.toString(), equals(ResultFile.getMaltyTable(2)));
    }
}