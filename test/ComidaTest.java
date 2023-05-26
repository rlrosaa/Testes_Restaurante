import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ComidaTest {

    Pizza pizza;
    Pizza pizza2;
    
    @BeforeEach
    public void prepare(){
        pizza = new Pizza();
        pizza2 = new Pizza("m");
    }

    @Test
    public void calculaPrecoBaseCorreto(){
        assertEquals(BigDecimal.valueOf(25).setScale(2),pizza.precoFinal( ));
    }

    @Test
    public void adicionarIngredienteDescricao(){
        pizza.adicionarIngrediente(1);
        assertTrue(pizza.toString().contains("1 adicionais"));
    }

    @Test
    public void adicionarIngredientePreco(){
        pizza.adicionarIngrediente(2);
        assertEquals(BigDecimal.valueOf(33).setScale(2),pizza.precoFinal( ));
    }

    @Test
    public void adicionarIngredientePrecoAcimaDeOito(){
        pizza.adicionarIngrediente(9);
        assertEquals(BigDecimal.valueOf(25).setScale(2),pizza.precoFinal( ));
    }

    @Test
    public void retirarIngredientePreco(){
        pizza.adicionarIngrediente(2);
        pizza.retirarIngrediente(1);
        assertEquals(BigDecimal.valueOf(29).setScale(2),pizza.precoFinal( ));
        
    }

    @Test
    public void retirarIngredienteMenorQueZero(){
        pizza.retirarIngrediente(5);
        assertEquals(BigDecimal.valueOf(25).setScale(2),pizza.precoFinal( ));
    }

    @Test
    public void comparaPizzasIguais(){
        assertEquals(0, pizza.compareTo(pizza2));
    }

    @Test
    public void comparaPizzasDiferentes(){
        pizza2.adicionarIngrediente(2);
        assertEquals(1, pizza.compareTo(pizza2));
    }

    
}
