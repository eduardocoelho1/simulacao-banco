/**
 * Representa um mapa com todos os itens que participam da simulacao
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Mapa {
    private Elemento[][] itens;
    private int largura;
    private int altura;
    
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
    
}
