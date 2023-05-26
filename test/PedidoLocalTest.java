import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PedidoLocalTest {

    private Pedido pedido;
    private Comida comida;

    @BeforeEach
    public void setup() {
        comida = new Pizza();
        pedido = new Pedido(comida);
    }

    @Test
    public void testAddComida() {
        assertEquals(1, pedido.itens.size());
    }

    @Test
    public void testListaComida() {
        assertEquals(comida, pedido.itens.get(0));
    }

    @Test
    public void testValorItens() {
        Comida comida2 = new Sanduiche();
        pedido.addComida(comida2);
        BigDecimal expectedValorItens = new BigDecimal(38.0).setScale(2);
        assertEquals(expectedValorItens, pedido.valorItens());
    }

    @Test
    public void testValorTaxa() {
        Comida comida2 = new Sanduiche();
        pedido.addComida(comida2);
        BigDecimal expectedValorItens = new BigDecimal(3.80).setScale(2, RoundingMode.HALF_UP  ); //10%
        assertEquals(expectedValorItens, pedido.valorTaxa());
    }
}