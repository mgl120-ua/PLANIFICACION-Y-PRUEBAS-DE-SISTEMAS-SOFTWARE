package ejercicio1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;


public class TestMultipleExample {
    int a;
    int b;
    int c;
    int esperado;
    int real;

    // a > 5 , a < 5
    // b > 5 , b < 5
    @Test
    public void multiPathAC1(){
        MultipathExample example = new MultipathExample();
        a = 10;
        b = 10;
        c = 0;
        esperado = 20;
        real = example.multiPath1(a,b,c);
        assertEquals(esperado, real);
    }
    @Test
    public void multiPathAC2(){
        MultipathExample example = new MultipathExample();
        a = 4;
        b = 4;
        c = 0;
        esperado = 0;
        real = example.multiPath1(a,b,c);
        assertEquals(esperado, real);
    }

    @Test
    public void multiPathAC3(){
        MultipathExample example = new MultipathExample();
        a = 3;
        b = 6;
        c = 2;
        esperado = 8;
        real = example.multiPath1(a,b,c);
        assertEquals(esperado, real);
    }
    @ParameterizedTest
    @MethodSource("argsMultipath2Shortcircuit")
    public void testC4(int a, int b, int c, int expectedResult) {
        Assertions.assertEquals(expectedResult, new MultipathExample().multiPath2(a,b,c));
    }
    private static Stream<Arguments> argsMultipath2Shortcircuit() {
        return Stream.of(
                // true a,b,c
                Arguments.of(6,4,6,16),
                // false a c
                Arguments.of(4,6,4,4),
                // false b
                Arguments.of(6,6,4,4)
        );
    }


    @ParameterizedTest
    @MethodSource("argsMultipath2")
    public void testC5(int a, int b, int c, int expectedResult) {
        Assertions.assertEquals(expectedResult, new MultipathExample().multiPath3(a,b,c));
    }
    private static Stream<Arguments> argsMultipath2() {
        return Stream.of(
                // true a b c
                Arguments.of(10,3,10,23),
                // false a b c
                Arguments.of(3,10,3,3));
    }

}
