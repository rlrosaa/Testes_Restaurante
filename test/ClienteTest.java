import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClienteTest {

    Cliente cliente;
    Pedido pedido;
    Comida comida;

    @BeforeEach
    public void prepare(){
        comida = new Pizza();
        pedido = new Pedido(comida);
        cliente = new Cliente("11111111111", "teste");
    }
    @Test
    public void registraPedidoCerto(){
        cliente.registrarPedido(pedido);
        assertEquals(1,cliente.todosOsPedidos().size());
    }

    @Test
    public void registraPedidoNull(){
        cliente.registrarPedido(null);
        assertEquals(0,cliente.todosOsPedidos().size());
    }

    @Test
    public void testToString(){
        cliente.registrarPedido(pedido);
        boolean testString = cliente.toString().contains("Total de pedidos: 1");
        assertTrue(testString);
    }
    
}
