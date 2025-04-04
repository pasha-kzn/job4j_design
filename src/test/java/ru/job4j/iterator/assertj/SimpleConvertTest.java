package ru.job4j.iterator.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;
import ru.job4j.assertj.SimpleConvert;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("one", "two", "three", "four", "one");
        assertThat(list).hasSize(5)
                .containsOnly("one", "two", "four", "three")
                .containsAnyOf("three", "eight", "nine")
                .anySatisfy(element -> {
                    assertThat(element).isEqualTo("three");
                    assertThat(element.length()).isGreaterThan(4);
                });
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> list = simpleConvert.toSet("one", "two", "three", "four", "one");
        assertThat(list).hasSize(4)
                .containsOnly("one", "two", "three", "four")
                .doesNotContain("seven", "six")
                .containsAnyOf("three", "eight", "nine");
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("one", "two", "three", "four", "five");
        assertThat(map).hasSize(5)
                .containsKeys("two", "three")
                .containsValues(1, 4, 0)
                .doesNotContainKey("six")
                .doesNotContainValue(5)
                .containsEntry("four", 3);
    }
}