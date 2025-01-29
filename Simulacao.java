import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Responsavel pela simulacao.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Simulacao {
    private List<Cliente> clientes;
    private JanelaSimulacao janelaSimulacao;
    private Mapa mapa;
    
    public Simulacao() {
        mapa = new Mapa();
        clientes = new ArrayList<>(); 
        criarCaixas(5,4);        
        criarParedes();// cria paredes, entradas e saidas
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
        if(valor % 10 == 0){
            Cliente novoCliente = new ClienteComum(new Localizacao(0,mapa.getAltura()-1), 0, 10, mapa);//Cria um cliente
            clientes.add(novoCliente);
            mapa.adicionarItem(novoCliente);
        }
        else if (valor % 5 == 0){
            Cliente novoCliente = new ClientePreferencial(new Localizacao(mapa.getLargura()-1,mapa.getAltura()-1), 0, 10, mapa);//Cria um cliente
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

    private void criarCaixas(int numeroCaixasComuns, int numeroCaixasPreferenciais) {
        int y = mapa.getAltura()/4;
        int espacoCaixasComuns = 2*numeroCaixasComuns;
        if (espacoCaixasComuns <= mapa.getLargura()/2-1) {
            for (int x = mapa.getLargura()/2-espacoCaixasComuns; x < mapa.getLargura()/2-1; x+=2) {
                mapa.addCaixa(new Localizacao(x, y), TipoAtendimento.Comum);
            }
        } else {
            throw new TooManyATMsException("Número de caixas comuns grande demais");
        }

        int espacoCaixasPreferenciais = 2*numeroCaixasPreferenciais;
        if (espacoCaixasPreferenciais <= mapa.getLargura()/2-1) {
            for (int x = mapa.getLargura()/2+espacoCaixasPreferenciais-1; x > mapa.getLargura()/2; x-=2) {
                mapa.addCaixa(new Localizacao(x, y), TipoAtendimento.Preferencial);
            }
        } else {
            throw new TooManyATMsException("Número de caixas preferenciais grande demais");
        }
    }

    public Mapa getMapa() {
        return mapa;
    } 
}
