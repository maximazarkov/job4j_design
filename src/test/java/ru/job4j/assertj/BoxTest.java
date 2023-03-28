package ru.job4j.assertj;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class BoxTest {

    @Test
    void isThisSphereArea() {
        int a = 10;
        Box box = new Box(0, a);
        double result = box.getArea();
        double expected = 4 * Math.PI * (a * a);
        assertThat(result).isEqualTo(expected, withPrecision(0.006d))
                .isCloseTo(1256.63d, withPrecision(0.01d))
                .isCloseTo(1256.63d, Percentage.withPercentage(1.0d))
                .isGreaterThan(1256.63d)
                .isLessThan(1256.64d);
    }

    @Test
    void isThisTetrahedronArea() {
        int a = 10;
        Box box = new Box(4, a);
        double result = box.getArea();
        double expected = Math.sqrt(3) * (a * a);
        assertThat(result).isEqualTo(expected, withPrecision(0.006d))
                .isCloseTo(173.20d, withPrecision(0.01d))
                .isCloseTo(173.20d, Percentage.withPercentage(1.0d))
                .isGreaterThan(173.20d)
                .isLessThan(173.21d);
    }

    @Test
    void isThisCubeArea() {
        int a = 10;
        Box box = new Box(8, a);
        double result = box.getArea();
        double expected = 6 * (a * a);
        assertThat(result).isEqualTo(expected, withPrecision(0.006d))
                .isCloseTo(600.0d, withPrecision(0.01d))
                .isCloseTo(600.0d, Percentage.withPercentage(1.0d))
                .isGreaterThan(599.9d)
                .isLessThan(600.1d);
    }

    @Test
    void isThisUnknownArea() {
        Box box = new Box(7, 10);
        double result = box.getArea();
        assertThat(result).isEqualTo(0, withPrecision(0.006d));
    }

    @Test
    void isThisBoxIsExists0() {
        Box box = new Box(0, 10);
        boolean result = box.isExist();
        assertThat(result).isTrue();
    }

    @Test
    void isThisBoxIsExists4() {
        Box box = new Box(4, 10);
        boolean result = box.isExist();
        assertThat(result).isTrue();
    }

    @Test
    void isThisBoxIsNotExistsVertexLess0() {
        Box box = new Box(-4, 10);
        boolean result = box.isExist();
        assertThat(result).isFalse();
    }

    @Test
    void isThisBoxIsNotExistsEdgeLess0() {
        Box box = new Box(4, -10);
        boolean result = box.isExist();
        assertThat(result).isFalse();
    }

    @Test
    void isThisNumberOfVertices0() {
        Box box = new Box(0, 10);
        int result = box.getNumberOfVertices();
        assertThat(result).isEqualTo(0)
                .isZero()
                .isGreaterThan(-1)
                .isLessThan(1);
    }

    @Test
    void isThisNumberOfVertices8() {
        Box box = new Box(8, 10);
        int result = box.getNumberOfVertices();
        assertThat(result).isEqualTo(8)
                .isNotZero()
                .isPositive()
                .isGreaterThan(7)
                .isLessThan(9);
    }

    @Test
    void isThisNumberOfVerticesNegativeWhenVertexLess0() {
        Box box = new Box(-8, 10);
        int result = box.getNumberOfVertices();
        assertThat(result).isEqualTo(-1)
                .isNotZero()
                .isNegative()
                .isGreaterThan(-2)
                .isLessThan(0);
    }

    @Test
    void isThisNumberOfVerticesNegativeWhenEggLess0() {
        Box box = new Box(0, -50);
        int result = box.getNumberOfVertices();
        assertThat(result).isEqualTo(-1)
                .isNotZero()
                .isNegative()
                .isGreaterThan(-2)
                .isLessThan(0);
    }

    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String result = box.whatsThis();
        assertThat(result).isEqualTo("Sphere")
                .isNotEmpty()
                .isNotBlank();
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 10);
        String result = box.whatsThis();
        assertThat(result).isEqualTo("Tetrahedron")
                .isNotEmpty()
                .isNotBlank();
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 10);
        String result = box.whatsThis();
        assertThat(result).isEqualTo("Cube")
                .isNotEmpty()
                .isNotBlank();
    }

    @Test
    void isThisUnknown() {
        Box box = new Box(7, 10);
        String result = box.whatsThis();
        assertThat(result).isEqualTo("Unknown object")
                .isNotEmpty()
                .isNotBlank()
                .containsIgnoringCase("obj")
                .contains("known", "obj")
                .doesNotContain("javascript")
                .startsWith("Un")
                .startsWithIgnoringCase("u")
                .endsWith("t");
    }
}