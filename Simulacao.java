import java.util.Random;
import java.util.List;

/**
 * Responsavel pela simulacao.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Simulacao {
    private Veiculo veiculo;
    private Caixa[] caixa;
    private List<Cliente> clientes;
    private JanelaSimulacao janelaSimulacao;
    private Mapa mapa;
    
    public Simulacao() {
        Random rand = new Random(12345);
        mapa = new Mapa();
        int largura = mapa.getLargura();
        int altura = mapa.getAltura();
        caixa = new Caixa[2];
        caixa[0] = new Caixa(new Localizacao(Math.round(largura/2*0.5f), Math.round(altura/2*0.5f)));// caixa 0 fica 50% pra esquerda do meio e pra cima
        caixa[1] = new Caixa(new Localizacao(Math.round(largura/2*1.5f), Math.round(altura/2*0.5f)));// caixa 1 fica 50% pra direita do meio e pra cima
        veiculo = new Veiculo(new Localizacao(rand.nextInt(largura),rand.nextInt(altura)));//Cria um veiculo em uma posicao aleatoria
        veiculo.setLocalizacaoDestino(new Localizacao(rand.nextInt(largura),rand.nextInt(altura)));//Define a posicao destino aleatoriamente
        mapa.adicionarItem(caixa[0]);//Inicializando o mapa com o veículo
        mapa.adicionarItem(caixa[1]);//Inicializando o mapa com o veículo

        janelaSimulacao = new JanelaSimulacao(mapa);
    }
    
    public void executarSimulacao(int numPassos){
        janelaSimulacao.executarAcao();
        for (int i = 0; i < numPassos; i++) {
            executarUmPasso();
            esperar(100);
        }        
    }

    private void executarUmPasso() {

        janelaSimulacao.executarAcao();
    }
    
    private void esperar(int milisegundos){
        try{
            Thread.sleep(milisegundos);
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
    
}
