import java.util.List;
import java.util.Stack;

public class Cliente {
    public final String CPF;
    private String nome;
    private Stack<Pedido> pedidos;

    /**
     * Construtor para cliente com nome e CPF. CPF é um atributo final.
     * Ainda precisa melhorar: validação dos dados.
     * @param CPF CPF do cliente (final)
     * @param nome Nome do cliente
     */
    public Cliente (String CPF, String nome){
        this.CPF = CPF;
        this.nome = nome;
        this.pedidos = new Stack<>();
    }

    /**
     * Registra um pedido para o cliente, desde que não seja nulo. Neste caso, ignora.
     * @param pedido O pedido a ser registrado. Não deve ser nulo.
     */
    public void registrarPedido(Pedido pedido){
        if(pedido!=null)
            this.pedidos.push(pedido);
    }

    /**
     * Retorna um extrato resumido com todos os pedidos do cliente. 
     * Cada pedido terá sua data, seu número e valor pago.
     * @return String com as informações de data, número e valor pago para todos os
     * pedidos do cliente.
     */
    public String extrato(){
        return "";
    }

    /**
     * Retorna um extrato resumido com os pedidos do cliente entre duas datas, incluindo
     * estas duas. 
     * Cada pedido terá sua data, seu número e valor pago.
     * @param inicio Data para o início do extrato (inclusiva)
     * @param fim Data para o fim do extrato (inclusiva) 
     * @return String com as informações de data, número e valor pago para todos os
     * pedidos do cliente.
     */
    public String extrato(Data inicio, Data fim){
        return "";
    }

    /**
     * Retorna uma lista imutável com a cópia de todos os pedidos do Cliente. 
     * A lista é imutável para não permitir alteração de pedidos 'por fora' da regra.
     * @return Lista com a cópia de todos os pedidos do Cliente. 
     */
    public List<Pedido> todosOsPedidos(){
        return  List.copyOf(this.pedidos);
    }

    /**
     * Igualdade do cliente: CPF iguais.
     * @param o Objeto cliente a ser comparado
     * @return TRUE para clientes iguais, FALSE caso contrário
     */
    @Override
    public boolean equals(Object o ){
        return this.CPF.equals(((Cliente)o).CPF);
    }

    /**
     * Descrição do cliente em string: dados pessoais, total de pedidos e gasto total.
     * @return String com dados do cliente, total de pedidos e gasto total.
     */
    @Override
    public String toString(){
        StringBuilder aux = new StringBuilder("Cliente: "+this.nome + "("+this.CPF+")");
        aux.append("\n\tTotal de pedidos: "+this.pedidos.size());
        aux.append("\n\tTotal gasto: R$ "+this.pedidos.stream()
                                                      .mapToDouble(Pedido::valorTotal)
                                                      .sum());
       return aux.toString();                                               
    }
}
