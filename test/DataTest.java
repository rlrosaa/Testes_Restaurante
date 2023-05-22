import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DataTest {

    @Test
    public void testAcrescentaDias() {
        // Criar uma instância de Data com valores válidos
        Data data = new Data(20, 05, 2022);
        
        // Acrescentar 5 dias à data
        Data dataAcrescentada = data.acrescentaDias(5);
        
        // Verificar se os valores da data acrescentada correspondem aos valores esperados
        Assertions.assertEquals("25/05/2022", dataAcrescentada.toString());
    }

    


    @Test
    public void testAnoBissexto() {
        // Criar uma instância de Data com um ano bissexto
        Data dataBissexto = new Data(29, 2, 2020);
        
        // Verificar se o método anoBissexto retorna true para a data com ano bissexto
        Assertions.assertTrue(dataBissexto.anoBissexto());
        
        // Criar uma instância de Data com um ano não bissexto
        Data dataNaoBissexto = new Data(28, 2, 2021);
        
        // Verificar se o método anoBissexto retorna false para a data sem ano bissexto
        Assertions.assertFalse(dataNaoBissexto.anoBissexto());
    }

    @Test
    public void testEhNaFrenteDe() {
        // Criar duas instâncias de Data para comparar
        Data data1 = new Data(20, 5, 2023);
        Data data2 = new Data(15, 5, 2023);
        
        // Verificar se o método ehNaFrenteDe retorna true quando a primeira data está na frente
        Assertions.assertTrue(data1.ehNaFrenteDe(data2));
        
        // Verificar se o método ehNaFrenteDe retorna false quando a segunda data está na frente
        Assertions.assertFalse(data2.ehNaFrenteDe(data1));
    }

    @Test
    public void testDataFormatada() {
        // Chamar o método dataFormatada para obter a data formatada
        Data dataFormatada = new Data(10, 05, 2023);
        
        // Verificar se a data formatada está correta
        Assertions.assertEquals("10/05/2023", dataFormatada.toString());
    }

    @Test
    public void testDataFutura() {
        // Criar duas instâncias de Data para comparar
        Data data1 = new Data(10, 5, 2023);
        Data data2 = new Data(15, 5, 2023);
        
        // Chamar o método dataFutura para obter a data futura entre as duas
        Data dataFutura = Data.dataFutura(data1, data2);
        
        // Verificar se a data futura está correta
        Assertions.assertEquals(data2, dataFutura);
    }
}
