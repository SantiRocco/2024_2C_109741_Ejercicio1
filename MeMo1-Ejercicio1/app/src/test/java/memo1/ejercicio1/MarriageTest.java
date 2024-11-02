package memo1.ejercicio1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;

// Pruebas unitarias

class MarriageTest {

    @Test
    void marriageShouldSaveAllDataCorrectly() {
        int spouse1Dni = 5783244;
        int spouse2Dni = 6036859;
        LocalDate marriageDate = LocalDate.of(2020, Month.JANUARY, 1);

        Marriage marriage = new Marriage(marriageDate, spouse1Dni, spouse2Dni);

        assertEquals(marriageDate, marriage.getDate());
        assertEquals(spouse1Dni, marriage.getFirstSpouseDni());
        assertEquals(spouse2Dni, marriage.getSecondSpouseDni());

        assertNotEquals(spouse2Dni, marriage.getFirstSpouseDni());
        assertNotEquals(spouse1Dni, marriage.getSecondSpouseDni());
    }

    @Test
    void marriageShouldDetectOnlySpousesAsInMarriage() {
        int spouse1Dni = 5783244;
        int spouse2Dni = 6036859;
        LocalDate marriageDate = LocalDate.of(2020, Month.JANUARY, 1);

        int notASpouse = 10000000;

        Marriage marriage = new Marriage(marriageDate, spouse1Dni, spouse2Dni);

        assertTrue(marriage.isInMarriage(spouse1Dni));
        assertTrue(marriage.isInMarriage(spouse2Dni));
        
        assertFalse(marriage.isInMarriage(notASpouse));
    }

    @Test
    void constructorShouldThrowExceptionIfOneDniIsNegative() {
        int spouse1Dni = -1;
        int spouse2Dni = 6036859;
        LocalDate marriageDate = LocalDate.of(2020, Month.JANUARY, 1);

        assertThrows(IllegalArgumentException.class, () -> new Marriage(marriageDate, spouse1Dni, spouse2Dni));
    }

    @Test
    void constructorShouldThrowExceptionIfOneDniIsZero() {
        int spouse1Dni = 0;
        int spouse2Dni = 6036859;
        LocalDate marriageDate = LocalDate.of(2020, Month.JANUARY, 1);

        assertThrows(IllegalArgumentException.class, () -> new Marriage(marriageDate, spouse1Dni, spouse2Dni));
    }

    @Test
    void constructorShouldThrowExceptionIfBothDnisAreEqual() {
        int spouse1Dni = 11222333;
        int spouse2Dni = spouse1Dni;
        LocalDate marriageDate = LocalDate.of(2020, Month.JANUARY, 1);
        assertThrows(IllegalArgumentException.class, () -> new Marriage(marriageDate, spouse1Dni, spouse2Dni));
    }
}
