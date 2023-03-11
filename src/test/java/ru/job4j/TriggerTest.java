package ru.job4j;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class TriggerTest {

    @Test
    public void test() {
        assertThat(new Trigger().someLogic()).isEqualTo(1);
    }
}