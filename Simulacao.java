import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Responsável pela simulação.
 */
public class Simulacao {
    private List<Cliente> clientes;
    private JanelaSimulacao janelaSimulacao;
    private Mapa mapa;
    private int geracaoClientesComuns;
    private int geracaoClientesPreferenciais;
    private int tempoAtendMinClienteComum;
    private int tempoAtendMaxClienteComum;
    private int tempoAtendMinClientePreferencial;
    private int tempoAtendMaxClientePreferencial;
    
    public Simulacao(int geracaoClientesComuns, int geracaoClientesPreferenciais,
    int tempoAtendMinClienteComum, int tempoAtendMaxClienteComum, int tempoAtendMinClientePreferencial,
    int tempoAtendMaxClientePreferencial, int numeroCaixasComuns, int numeroCaixasPreferenciais) {
        this.geracaoClientesComuns = geracaoClientesComuns;
        this.geracaoClientesPreferenciais = geracaoClientesPreferenciais;
        this.tempoAtendMinClienteComum = tempoAtendMinClienteComum;
        this.tempoAtendMaxClienteComum = tempoAtendMaxClienteComum;
        this.tempoAtendMinClientePreferencial = tempoAtendMinClientePreferencial;
        this.tempoAtendMaxClientePreferencial = tempoAtendMaxClientePreferencial;
        mapa = new Mapa();
        clientes = new ArrayList<>(); 
        criarCaixas(numeroCaixasComuns,numeroCaixasPreferenciais);        
        criarParedes();
        janelaSimulacao = new JanelaSimulacao(mapa);
    }
    
    /**
     * Inicia a simulação.
     * @param numPassos: número de passos da simulação.
     */
    public void executarSimulacao(int numPassos){
        janelaSimulacao.executarAcao();
        for (int i = 0; i < numPassos; i++) {
            executarUmPasso();
            esperar(100);
        }        
    }

    /**
     * Executa um passo da simulação.
     */
    private void executarUmPasso() {
        Iterator<Cliente> it = clientes.iterator();
        while (it.hasNext()) {
            Cliente cliente = it.next();
            mapa.removerItem(cliente);
            cliente.executarAcao();
            if (cliente.getEstado() != Cliente.Estado.sair) {
                mapa.adicionarItem(cliente);
            } else {
                it.remove();
            }
        }
        Random rand = new Random();
        int valor = rand.nextInt(100);
        if(valor % geracaoClientesComuns == 0){
            Cliente novoCliente = new ClienteComum(new Localizacao(0,mapa.getAltura()-1), tempoAtendMinClienteComum, tempoAtendMaxClienteComum, mapa);//Cria um cliente
            clientes.add(novoCliente);
            mapa.adicionarItem(novoCliente);
        }
        if(valor % geracaoClientesPreferenciais == 0){
            Cliente novoCliente = new ClientePreferencial(new Localizacao(mapa.getLargura()-1,mapa.getAltura()-1), tempoAtendMinClientePreferencial, tempoAtendMaxClientePreferencial, mapa);//Cria um cliente
            clientes.add(novoCliente);
            mapa.adicionarItem(novoCliente);
        }
        janelaSimulacao.executarAcao();
    }


    private void esperar(int milisegundos){
        try{
            Thread.sleep(milisegundos);
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Adiciona paredes, entrada e saída no mapa.
     */
    private void criarParedes() {
        int largura = mapa.getLargura();
        for (int i = 0; i < largura; i++) {
            if (i < largura/2-1 || i > largura/2) {
                mapa.adicionarItem(new Parede(new Localizacao(i, mapa.getAltura()-3)));
                mapa.adicionarItem(new Parede(new Localizacao(i, 3)));
            } else if (i == largura/2-1) {
                mapa.setEntrada(new Localizacao(i, mapa.getAltura()-3), TipoAtendimento.Comum);
                mapa.setSaida(new Localizacao(i, 3), TipoAtendimento.Comum);
            } else {
                mapa.setEntrada(new Localizacao(i, mapa.getAltura()-3), TipoAtendimento.Preferencial);
                mapa.setSaida(new Localizacao(i, 3), TipoAtendimento.Preferencial);
            }
        }

        for (int i = 3; i <= mapa.getAltura()-3; i++) {
            mapa.adicionarItem(new Parede(new Localizacao(0, i)));
            mapa.adicionarItem(new Parede(new Localizacao(mapa.getLargura()-1, i)));
        }
    }

    /**
     * Adiciona os caixas no mapa.
     * @param numeroCaixasComuns: quantidade de caixas comuns.
     * @param numeroCaixasPreferenciais: quantidade de caixas preferenciais.
     */
    private void criarCaixas(int numeroCaixasComuns, int numeroCaixasPreferenciais) {
        int y = mapa.getAltura()/4;
        for (int x = mapa.getLargura()/2-2*numeroCaixasComuns; x < mapa.getLargura()/2-1; x+=2) {
            mapa.addCaixa(new Localizacao(x, y), TipoAtendimento.Comum);
        }
        for (int x = mapa.getLargura()/2+2*numeroCaixasPreferenciais-1; x > mapa.getLargura()/2; x-=2) {
            mapa.addCaixa(new Localizacao(x, y), TipoAtendimento.Preferencial);
        }
    }

    public Mapa getMapa() {
        return mapa;
    } 
}
