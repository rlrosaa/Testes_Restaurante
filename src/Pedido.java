import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/** 
 * MIT License
 *
 * Copyright(c) 2022-23 João Caram <caram@pucminas.br>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

 /**
  * Classe pedido: composição com "comida" e delegação para valores de taxa e final. Implementa Comparable para prover ordenação.
  */
public class Pedido implements Comparable<Pedido>{
    private static Random sorteio = new Random(42);     //somente para demonstração do Comparable
    private static final Data inicio = new Data(1,1,2023);
    
    private static int proximo_id = 1;          //para id automático
    private int idPedido;
    private Data dataPedido;
    private ComparableList<Comida> itens;
    private IPedido categoriaPedido;

    // public static void setProximoPedido(int id){
    //     if(id>=proximo_id)
    //         proximo_id = id;
    // }
    
    /**
     * Apenas para encapsular a criação da lista de comidas
     */
    private void initLista(){
        this.itens = new ComparableList<>();
    }

    /**
     * Inicializador: recebe a comida para iniciar a lista e a categoria do Pedido (delegação para a interface)
     * @param primeira Primeira comida para abrir o pedido
     * @param categoria Categoria do pedido, calcula taxa e valor final
     */
    private void init(Comida primeira, IPedido categoria){
        this.idPedido = proximo_id;
        proximo_id++;
        this.dataPedido = inicio.acrescentaDias(sorteio.nextInt(120)); //somente para demonstração do Comparable;
        this.categoriaPedido = categoria;
        this.addComida(primeira);

    }

    
    /**
     * Construtor. Um pedido só pode ser iniciado se já houver uma comida para ele
     * @param quando  Data de criação do pedido
     * @param primeira Comida inicial do pedido
     * @deprecated Use o construtor mais genérico, com Comida como parâmetro.
     */
    @Deprecated
    public Pedido(Pizza primeira) throws IllegalArgumentException{
        initLista();
        init(primeira, new PedidoLocal(this.itens));
    }

    /**
     * Construtor. Um pedido só pode ser iniciado se já houver uma comida para ele
     * @param quando  Data de criação do pedido
     * @param primeira Comida inicial do pedido
     */
    public Pedido(Comida primeira) throws IllegalArgumentException{
        initLista();
        init(primeira, new PedidoLocal(this.itens));
    }

    /**
     * Construtor. Um pedido só pode ser iniciado se já houver uma comida para ele
     * @param quando  Data de criação do pedido
     * @param primeira Comida inicial do pedido
     */
    public Pedido(Comida primeira, double distancia) throws IllegalArgumentException{
        initLista();
        init(primeira, new PedidoDelivery(this.itens, distancia));
    }


    /**
     * Adiciona uma comida ao pedido, usando JCF
     * @param novoItem Comida a ser adicionada
     * @deprecated Utilize o método mais genérico addComida
     */
    @Deprecated
    public void addItem(Pizza novoItem){
       this.addComida(novoItem);
    }

     /**
     * Adiciona uma comida ao pedido, usando JCF
     * @param novoItem Comida a ser adicionada
     */
    public void addComida(Comida novoItem){
        this.categoriaPedido.addComida(novoItem);
  }

    /**
     * Remove uma comida do pedido, se não for a última.
     * @param novoItem Comida a ser removida, usando JCF
     */
    public void cancelaItem(int posicao){
        if(this.itens.size()>1)
            if(posicao>=0 && posicao<this.itens.size()){
                Comida saindo = this.itens.get(posicao);
                this.itens.remove(saindo);
            }
                
     }

     
     /**
     * Localiza uma comida no pedido
     * @param posicao Comida ser localizada (posição)
     * @return Pizza na posição indicada (ou null se não houver)
     * @deprecated Prefira o método mais abstrato getComida
     */
    @Deprecated
    public Pizza getItem(int posicao){
        return (Pizza)getComida(posicao);
     }

     /**
     * Localiza uma comida no pedido
     * @param posicao Comida a ser localizada (posição)
     * @return Comida na posição indicada (ou null se não houver)
      */
    public Comida getComida(int posicao){
        Comida retorno = null;
        if(posicao>=0 && posicao<this.itens.size())
           retorno =  this.itens.get(posicao);
        return retorno;
    }

    /**
     * O valor dos itens do pedido é a soma do valor das comidas que ele contém.
     * @return BigDecimal de escala 2 com o valor do pedido
     */
     protected BigDecimal valorItens(){
        BigDecimal valor = new BigDecimal(0.0).setScale(2, RoundingMode.HALF_UP);
        for(Comida comida : itens) {
            valor = valor.add(comida.precoFinal());
        }
        return valor;
     }

    /**
     * Valor total do pedido, incluindo a taxa de 10% de serviço.
     * @return Double com o valor total do pedido
     */
    public double valorTotal(){
        return valorItens().add(valorTaxa()).doubleValue();
    }

    /**
     * Classe de relatório do pedido. (a ser melhorado)
     * @return String com detalhamento do pedido. 
     */
    @Override
    public String toString(){
        StringBuilder relat = new StringBuilder("");
        this.itens.sort();
        relat.append("=====================\n");
        relat.append("Pedido nº "+this.idPedido+" - "+this.dataPedido+"\n");
        int cont=1;
        for (Comida comida : this.itens) {
            relat.append(String.format("%02d", cont)+" - ");
            relat.append(comida.toString()+"\n");
            cont++;
        }
        relat.append("\nVALOR DOS ITENS:\t R$"+this.valorItens());
        relat.append("\nVALOR DA TAXA:  \t R$"+this.valorTaxa());
        relat.append("\nTOTAL DO PEDIDO:\t R$"+this.valorTotal()+"\n");
        relat.append("=====================\n");
        return relat.toString();  
    }

    /**
     * Indica a quantidade total de itens neste momento (bom para pensar: precisa mesmo disso?)
     * @return Inteiro com a quantidade de itens do pedido no momento.
     */
    public int quantidadeDeItens() {
        return this.itens.size();
    }


    /**
     * Retorna taxa de serviço para pedidos no local (atualmente em 10%).
     * @return Constante com a taxa de serviço atual.
     */
    protected BigDecimal valorTaxa(){
        return categoriaPedido.valorTaxa();
    }

    /**
     * Comparador padrão do pedido: quem tem a data mais futura é "maior". Segue regra do Comparable
     * @param o Outro pedido para comparação
     * @return -1, 0, 1, conforme a data deste é atrás, igual ou na frente da data do outro
     */
    @Override
    public int compareTo(Pedido o) {
        if(this.dataPedido.ehNaFrenteDe(o.dataPedido))
            return 1;
        else if(o.dataPedido.ehNaFrenteDe(this.dataPedido))
            return -1;
        return 0;
    }    
}

