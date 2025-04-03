package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import ru.job4j.iterator.assertj.SimpleModel;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SimpleModelTest {
    @Test
    void checkGetName() {
        SimpleModel simpleModel = new SimpleModel();
        assertThatThrownBy(simpleModel::getName)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void checkName() {
        SimpleModel simpleModel = new SimpleModel();
        assertThatThrownBy(() -> simpleModel.setName("name", 5))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void checkMessage() {
        SimpleModel simpleModel = new SimpleModel();
        String word = "name";
        int number = 5;
        assertThatThrownBy(() -> simpleModel.setName(word, number))
                /*проверяем класс исключения: */
                .isInstanceOf(IllegalArgumentException.class)
                /*проверяем факт наличия сообщения*/
                .message()
                .isNotEmpty();
    }
}