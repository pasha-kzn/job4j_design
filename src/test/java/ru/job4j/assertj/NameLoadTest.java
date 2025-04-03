package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import ru.job4j.iterator.assertj.NameLoad;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkIfNamesIsEmpty() {
        String[] names = new String[0];
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Names array is empty");
    }

    @Test
    void checkHasNotEqualSign() {
        String[] names = {""};
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("does not contain the symbol '='");
    }

    @Test
    void checkKeyNotExists() {
        String[] names = {"=value"};
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("does not contain a key");
    }

    @Test
    void checkValueNotExists() {
        String[] names = {"key="};
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("does not contain a value");
    }
}