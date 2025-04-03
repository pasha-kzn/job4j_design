package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import ru.job4j.iterator.assertj.NameLoad;

import static java.lang.String.format;
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
        String[] names = {"key:value"};
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(format("this name: %s does not contain the symbol '='", names[0]));
    }

    @Test
    void checkKeyNotExists() {
        String[] names = {"=value"};
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(format("this name: %s does not contain a key", names[0]));
    }

    @Test
    void checkValueNotExists() {
        String[] names = {"key="};
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(format("this name: %s does not contain a value", names[0]));
    }
}