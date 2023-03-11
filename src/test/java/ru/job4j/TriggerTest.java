package ru.job4j;

import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TriggerTest {

    @Test
    public void test() {
        assertThat(new Trigger().someLogic(), is(1));
    }

}