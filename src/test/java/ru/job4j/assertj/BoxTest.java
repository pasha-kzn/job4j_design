package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {

    @Test
    void whatsThisFirst() {
        Box box = new Box(0, 1);
        String type = box.whatsThis();
        assertThat("Sphere".equals(type)).isTrue();
    }

    @Test
    void whatsThisSecond() {
        Box box = new Box(2, 4);
        String type = box.whatsThis();
        assertThat("Unknown object".equals(type)).isTrue();
    }

    @Test
    void getNumberOfVerticesFirst() {
        Box box = new Box(4, 2);
        int number = box.getNumberOfVertices();
        assertThat((number == 4)).isTrue();
    }

    @Test
    void getNumberOfVerticesSecond() {
        Box box = new Box(8, 3);
        int number = box.getNumberOfVertices();
        assertThat((number == 8)).isTrue();
    }

    @Test
    void isExistFirst() {
        Box box = new Box(8, 5);
        boolean exists = box.isExist();
        assertThat(exists);
    }

    @Test
    void isExistSecond() {
        Box box = new Box(5, 6);
        boolean exists = box.isExist();
        assertThat(!exists);
    }

    @Test
    void getAreaFirst() {
        Box box = new Box(8, 6);
        double area = box.getArea();
        assertThat(area).isEqualTo(216.01, withPrecision(0.01d));
    }

    @Test
    void getAreaSecond() {
        Box box = new Box(0, 6);
        double area = box.getArea();
        assertThat(area).isEqualTo(452.01, withPrecision(0.38d));
    }
}