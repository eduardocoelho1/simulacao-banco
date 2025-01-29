import java.util.List;
import java.util.ArrayList;
/**
 * Representa um mapa com todos os itens que participam da simulacao
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Mapa {
    private Elemento[][] itens;
    private int largura;
    private int altura;
    private Localizacao entradaComum;
    private Localizacao entradaPref;
    private Localizacao saidaComum;
    private Localizacao saidaPref;
    private List<Localizacao> caixasComuns;
    private List<Localizacao> caixasPreferenciais;
    
    private static final int LARGURA_PADRAO = 35;
    private static final int ALTURA_PADRAO = 35;
    
    /**
     * Cria mapa para alocar itens da simulacao.
     * @param largura: largura da área de simulacao.
     * @param altura: altura da área de simulação.
     */
    public Mapa(int largura, int altura) {
        this.largura = largura;
        this.altura = altura;
        itens = new Elemento[altura][largura];
        caixasComuns = new ArrayList<>();
        caixasPreferenciais = new ArrayList<>();
    }
    /**
     * Cria mapa com tamanho padrao.
     */
    public Mapa(){
        this(LARGURA_PADRAO,ALTURA_PADRAO);
    }
    
    public void adicionarItem(Elemento v){
        itens[v.getLocalizacaoAtual().getX()][v.getLocalizacaoAtual().getY()] = v;
    }
    
    public void removerItem(Elemento v){
        itens[v.getLocalizacaoAtual().getX()][v.getLocalizacaoAtual().getY()] = null;
    }
    
    public void atualizarMapa(Elemento v){
        removerItem(v);
        adicionarItem(v);
    }
    
    public Elemento getItem(int x, int y){
        return itens[x][y];
    }

    public int getLargura() {
        return largura;
    }

    public int getAltura() {
        return altura;
    }

    public Localizacao getEntrada(TipoAtendimento tipo) {
        switch (tipo) {
            case TipoAtendimento.Comum:
                return entradaComum;
            case TipoAtendimento.Preferencial:
                return entradaPref;
            default:
                return null;
        }
    }

    public Localizacao getSaida(TipoAtendimento tipo) {
        switch (tipo) {
            case TipoAtendimento.Comum:
                return saidaComum;
            case TipoAtendimento.Preferencial:
                return saidaPref;
            default:
                return null;
        }
    }

    public void setEntrada(Localizacao entrada, TipoAtendimento tipo) {
        switch (tipo) {
            case TipoAtendimento.Comum:
                this.entradaComum = entrada;
                break;
            case TipoAtendimento.Preferencial:
                this.entradaPref = entrada;
            default:
                break;
        }
    }

    public void setSaida(Localizacao saida, TipoAtendimento tipo) {
        switch (tipo) {
            case TipoAtendimento.Comum:
                saidaComum = saida;
                break;
            case TipoAtendimento.Preferencial:
                saidaPref = saida;
            default:
                break;
        }
    }

    public void addCaixa(Localizacao localizacao, TipoAtendimento tipo) {
        switch (tipo) {
            case TipoAtendimento.Comum:
                caixasComuns.add(localizacao);
                break;
            case TipoAtendimento.Preferencial:
                caixasPreferenciais.add(localizacao);
                break;
            default:
                break;
        }

        adicionarItem(new Caixa(localizacao));
    }

    public List<Localizacao> getCaixas(TipoAtendimento tipo) {
        switch (tipo) {
            case TipoAtendimento.Comum:
                return caixasComuns;
            case TipoAtendimento.Preferencial:
                return caixasPreferenciais;
            default:
                return null;
        }
    }

    public Localizacao getMelhorCaixa(TipoAtendimento tipo) {
        List<Localizacao> caixas;
        switch (tipo) {
            case TipoAtendimento.Comum:
                caixas = caixasComuns;
                break;
            case TipoAtendimento.Preferencial:
                caixas = caixasPreferenciais;
                break;
            default:
                return null;
        }
        
        Localizacao melhorCaixa = caixas.get(0);
        int tamMenorFila = 1000;

        for (Localizacao caixa: caixas) {
            int y = caixa.getY()+1;
            int tamFila = 0;
            while (!(getItem(caixa.getX(), y) instanceof Parede)) {
                if (getItem(caixa.getX(), y) != null) {
                    tamFila++;
                }
                y++;
            }
            if (tamFila < tamMenorFila) {
                tamMenorFila = tamFila;
                melhorCaixa = caixa;
            }
        }
        return melhorCaixa;
    }
}
