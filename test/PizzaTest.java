import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PizzaTest {
    Pizza pizzaBasica;
    Pizza pizzaComAdicional;

    @BeforeEach  
    public void prepare(){
        pizzaBasica = new Pizza();
        pizzaComAdicional = new Pizza(2);
    }

    
    @Test
    public void calculaPrecoBaseCorreto(){
        assertEquals(BigDecimal.valueOf(25).setScale(2), pizzaBasica.precoFinal());
    }

    @Test
    public void deixaRetirarAdicionais(){
        pizzaComAdicional.retirarIngrediente(1);
        assertEquals(BigDecimal.valueOf(29).setScale(2), pizzaComAdicional.precoFinal());
    }

    @Test
    public void naoPodeTerAdicionaisNegativos(){
        pizzaBasica.retirarIngrediente(5);
        assertEquals(BigDecimal.valueOf(25).setScale(2), pizzaBasica.precoFinal());
    }

    @Test
    public void naoPodeTerAdicionaisAlemDoMaximo(){
        pizzaBasica.adicionarIngrediente(Pizza.MAX_ADICIONAIS_PIZZA+1);
        assertEquals(BigDecimal.valueOf(25).setScale(2), pizzaBasica.precoFinal());
    }

    @Test
    public void calculaPrecoCorretoComAdicionais(){
        pizzaBasica.adicionarIngrediente(2);
        assertEquals(BigDecimal.valueOf(33).setScale(2), pizzaBasica.precoFinal());
    }

    @Test
    public void deixaCriarPizzaComAdicionais(){
        assertEquals(BigDecimal.valueOf(33).setScale(2), pizzaComAdicional.precoFinal());
    }

    @Test
    public void geraNotaDeCompraMedia(){
        String nota = pizzaComAdicional.toString();
        assertTrue(nota.contains("médio")&&nota.contains("R$ 33"));
    }

    @Test
    public void geraNotaDeCompraPequena(){
        Pizza pizzaP = new Pizza("p");
        String nota = pizzaP.toString();
        assertTrue(nota.contains("pequeno"));
    }
}
