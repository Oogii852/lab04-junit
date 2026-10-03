package mn.edu.must.sqat;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class GradeCalculatorTest {

    @Test
    @DisplayName("Ердийн утгууд: 95→A, 85→B, 75→C, 65→D, 30→F")
    void typicalValues() {
        GradeCalculator calc = new GradeCalculator();   // Arrange
        String a = calc.letterGrade(95);                // Act
        String b = calc.letterGrade(85);
        String c = calc.letterGrade(75);
        String d = calc.letterGrade(65);
        String f = calc.letterGrade(30);
        assertAll(                                      // Assert
            () -> assertEquals("A", a),
            () -> assertEquals("B", b),
            () -> assertEquals("C", c),
            () -> assertEquals("D", d),
            () -> assertEquals("F", f));
    }

    @Test
    @DisplayName("90 оноо яг A дүн байх ёстой (хязгаарын тохиолдол)")
    void ninetyIsExactlyA() {
        GradeCalculator calc = new GradeCalculator();   // Arrange
        String grade = calc.letterGrade(90.0);          // Act
        assertEquals("A", grade);                       // Assert
    }

    @Test
    @DisplayName("89.99 оноо B дүн байх ёстой (A-аас доош хязгаар)")
    void eightyNinePointNineNineIsB() {
        GradeCalculator calc = new GradeCalculator();   // Arrange
        String grade = calc.letterGrade(89.99);         // Act
        assertEquals("B", grade);                       // Assert
    }

    @Test
    @DisplayName("60 оноо яг D дүн байх ёстой (хязгаарын тохиолдол)")
    void sixtyIsExactlyD() {
        GradeCalculator calc = new GradeCalculator();   // Arrange
        String grade = calc.letterGrade(60.0);          // Act
        assertEquals("D", grade);                       // Assert
    }

    @Test
    @DisplayName("59.99 оноо F дүн байх ёстой (D-ээс доош хязгаар)")
    void fiftyNinePointNineNineIsF() {
        GradeCalculator calc = new GradeCalculator();   // Arrange
        String grade = calc.letterGrade(59.99);         // Act
        assertEquals("F", grade);                       // Assert
    }

    @Test
    @DisplayName("0 оноо F дүн байх ёстой (доод хязгаар)")
    void zeroIsF() {
        GradeCalculator calc = new GradeCalculator();   // Arrange
        String grade = calc.letterGrade(0.0);           // Act
        assertEquals("F", grade);                       // Assert
    }

    @Test
    @DisplayName("100 оноо A дүн байх ёстой (дээд хязгаар)")
    void hundredIsA() {
        GradeCalculator calc = new GradeCalculator();   // Arrange
        String grade = calc.letterGrade(100.0);         // Act
        assertEquals("A", grade);                       // Assert
    }

    @Test
    @DisplayName("letterGrade: -1, 101 болон хязгаараас гарсан утгад exception шидэх ёстой")
    void letterGradeRejectsOutOfRange() {
        GradeCalculator calc = new GradeCalculator();   // Arrange
        assertAll(                                      // Act + Assert
            () -> assertThrows(IllegalArgumentException.class, () -> calc.letterGrade(-1)),
            () -> assertThrows(IllegalArgumentException.class, () -> calc.letterGrade(-0.01)),
            () -> assertThrows(IllegalArgumentException.class, () -> calc.letterGrade(101)),
            () -> assertThrows(IllegalArgumentException.class, () -> calc.letterGrade(100.01)));
    }

    @Test
    @DisplayName("totalScore: 10, 40, 10, 10, 30 → 100 нийлбэр гарах ёстой")
    void totalScoreMaximum() {
        GradeCalculator calc = new GradeCalculator();   // Arrange
        double total = calc.totalScore(10, 40, 10, 10, 30); // Act
        assertEquals(100.0, total, 1e-9);               // Assert
    }

    @Test
    @DisplayName("totalScore: ердийн утгуудын нийлбэр зөв байх ёстой")
    void totalScoreTypical() {
        GradeCalculator calc = new GradeCalculator();   // Arrange
        double total = calc.totalScore(8, 35, 7, 9, 25); // Act
        assertEquals(84.0, total, 1e-9);                // Assert
    }

    @Test
    @DisplayName("totalScore: сөрөг утга (att = -5) exception шидэх ёстой")
    void totalScoreRejectsNegative() {
        GradeCalculator calc = new GradeCalculator();   // Arrange
        assertThrows(IllegalArgumentException.class,    // Act + Assert
            () -> calc.totalScore(-5, 40, 10, 10, 30));
    }

    @Test
    @DisplayName("totalScore: дээд хязгаараас хэтэрсэн утга (lab = 41 гэх мэт) exception шидэх ёстой")
    void totalScoreRejectsOverMax() {
        GradeCalculator calc = new GradeCalculator();   // Arrange
        assertAll(                                      // Act + Assert
            () -> assertThrows(IllegalArgumentException.class, () -> calc.totalScore(10, 41, 10, 10, 30)),
            () -> assertThrows(IllegalArgumentException.class, () -> calc.totalScore(11, 40, 10, 10, 30)),
            () -> assertThrows(IllegalArgumentException.class, () -> calc.totalScore(10, 40, 11, 10, 30)),
            () -> assertThrows(IllegalArgumentException.class, () -> calc.totalScore(10, 40, 10, 11, 30)),
            () -> assertThrows(IllegalArgumentException.class, () -> calc.totalScore(10, 40, 10, 10, 31)));
    }
}
