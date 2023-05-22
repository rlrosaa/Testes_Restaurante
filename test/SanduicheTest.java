import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SanduicheTest {

    @Test
    public void testValorExtrasSemCombo() {
        // Sanduíche sem combo
        Sanduiche sanduicheSemCombo = new Sanduiche();
        assertEquals(BigDecimal.ZERO, sanduicheSemCombo.valorExtras());
    }
    @Test
    public void testValorExtrasComCombo() {
        // Sanduíche com combo
        Sanduiche sanduicheComCombo = new Sanduiche(true);
        BigDecimal expectedValorExtras = new BigDecimal(3.0).setScale(2, RoundingMode.HALF_UP);
        assertEquals(expectedValorExtras, sanduicheComCombo.valorExtras());
    }

    @Test
    public void testToString() {
        // Sanduíche sem combo, sem ser vegano
        Sanduiche sanduicheSemComboSemVegano = new Sanduiche();
        boolean testString = sanduicheSemComboSemVegano.toString().contains("Sanduíche");
        assertTrue(testString);
    }

    @Test
    public void testToStringCombo(){
        // Sanduíche com combo, sem ser vegano
        Sanduiche sanduicheComComboSemVegano = new Sanduiche(true);
        boolean temCombo = sanduicheComComboSemVegano.toString().contains("Combo: R$ 3.00");
        assertTrue(temCombo);
    }

    @Test
    public void testToStringVegano(){
        // Sanduíche sem combo, vegano
        Sanduiche sanduicheSemComboVegano = new Sanduiche();
        sanduicheSemComboVegano.opcaoVegana(true);
        boolean ehVegano = sanduicheSemComboVegano.toString().contains("vegano");
        assertTrue(ehVegano);
    }
    @Test
    public void testToStringComboVegano(){
        // Sanduíche com combo, vegano
        Sanduiche sanduicheComComboVegano = new Sanduiche(true);
        sanduicheComComboVegano.opcaoVegana(true);
        boolean ehVegano = sanduicheComComboVegano.toString().contains("vegano");
        boolean temCombo = sanduicheComComboVegano.toString().contains("Combo: R$ 3.00");
        assertTrue(ehVegano && temCombo);
    }   
}