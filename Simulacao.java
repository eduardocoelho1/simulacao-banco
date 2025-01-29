import java.util.Random;
import java.util.List;
import java.util.ArrayList;

/**
 * Responsavel pela simulacao.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Simulacao {
    private Caixa[] caixa;
    private List<Cliente> clientes;
    private Cliente cliente;
    private List<Parede> paredes;
    private JanelaSimulacao janelaSimulacao;
    private Mapa mapa;
    
    public Simulacao() {
        mapa = new Mapa();
        int largura = mapa.getLargura();
        int altura = mapa.getAltura();
        
        caixa = new Caixa[2];
        caixa[0] = new Caixa(new Localizacao(Math.round(largura/2*0.5f), Math.round(altura/2*0.5f)));// caixa 0 fica 50% pra esquerda do meio e pra cima
        caixa[1] = new Caixa(new Localizacao(Math.round(largura/2*1.5f), Math.round(altura/2*0.5f)));// caixa 1 fica 50% pra direita do meio e pra cima
        mapa.adicionarItem(caixa[0]);//Caixa comum
        mapa.adicionarItem(caixa[1]);//Caixa pref
        
        paredes = criarParedes();// cria paredes, entradas e saidas
        for (Parede parede: paredes) {
            mapa.adicionarItem(parede);
        }

        cliente = new Cliente(new Localizacao(0,altura-1), 1);//Cria um cliente
        cliente.setLocalizacaoDestino(mapa.getEntrada(TipoAtendimento.Comum));
        mapa.adicionarItem(cliente);

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
        mapa.removerItem(cliente);
        cliente.executarAcao();
        mapa.adicionarItem(cliente);
        janelaSimulacao.executarAcao();
    }
    
    private void esperar(int milisegundos){
        try{
            Thread.sleep(milisegundos);
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
    }

    private List<Parede> criarParedes() {
        List<Parede> paredes = new ArrayList<>();
        int largura = mapa.getLargura();
        for (int i = 0; i < largura; i++) {
            if (i < largura/2-1 || i > largura/2) {
                paredes.add(new Parede(new Localizacao(i, Math.round(mapa.getAltura()*0.1f))));
                paredes.add(new Parede(new Localizacao(i, Math.round(mapa.getAltura()*0.9f))));
            } else if (i == largura/2-1) {
                mapa.setEntrada(new Localizacao(i, Math.round(mapa.getAltura()*0.9f)), TipoAtendimento.Comum);
                mapa.setSaida(new Localizacao(i, Math.round(mapa.getAltura()*0.1f)), TipoAtendimento.Comum);
            } else {
                mapa.setEntrada(new Localizacao(i, Math.round(mapa.getAltura()*0.9f)), TipoAtendimento.Preferencial);
                mapa.setSaida(new Localizacao(i, Math.round(mapa.getAltura()*0.1f)), TipoAtendimento.Preferencial);
            }
        }
        return paredes;
    }
    
}
