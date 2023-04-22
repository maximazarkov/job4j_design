package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class SimpleModelTest {
    @Test
    void checkGetName() {
        SimpleModel sm = new SimpleModel();
        assertThatThrownBy(sm::getName)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void chakName() {
        SimpleModel sm = new SimpleModel();
        assertThatThrownBy(() -> sm.setName("neme", 56))
                .isInstanceOf(IllegalArgumentException.class);
    }

    /*Проверяем наличие текстового информационного сообщения, сопровождающее исключение:*/
    @Test
    void checkMessage() {
        SimpleModel sm = new SimpleModel();
        assertThatThrownBy(() -> sm.setName("neme", 5))
                /*проверяем класс исключения: */
                .isInstanceOf(IllegalArgumentException.class)
                /*проверяем факт наличия сообщения*/
                .message()
                .isNotEmpty();
    }

    /*проверим содержание текстового информационного сообщения, сопровождающее исключение:*/
    @Test
    void checkWordMessage() {
        SimpleModel sm = new SimpleModel();
        String word = "name";
        int number = 5;
        assertThatThrownBy(() -> sm.setName(word, number))
                /*проверяем класс исключения: */
                .isInstanceOf(IllegalArgumentException.class)
                /*с помощью регулярного выражения проверяем факт наличия сообщения*/
                .hasMessageMatching("^.+")
                /*проверяем, что в сообщении есть соответствующие параметры:*/
                .hasMessageContaining(word, number)
                /*проверяем наличие конкретного слова в сообщении:*/
                .hasMessageContaining("name");
    }
}