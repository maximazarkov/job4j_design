package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class NameLoadTest {
    @Test
    void whenWithoutValue() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("name1=value2", "name2="))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("not contain a value");
    }

    @Test
    void whenWithoutKey() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("name1=value2", "=value2"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("not contain a key");
    }

    @Test
    void whenWithoutEQL() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("name1=value2", "name2value2"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("not contain the symbol \"=\"");
        assertThatThrownBy(() -> nameLoad.parse("name1=value2", "name2:value2"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("not contain the symbol \"=\"");
    }

    @Test
    void checkLengthNameIsEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("is empty");
    }

    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }
}