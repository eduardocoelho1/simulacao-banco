import java.util.Random;

/**
 * Representa os veiculos da simulacao.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public abstract class Cliente extends Elemento {
    public enum Estado {
        fora,
        dentro,
        atendendo,
        atendido,
        saindo,
        sair
    }

    private Localizacao localizacaoDestino;
    private Estado estado;
    private int tempoAtend;
    private int tempoGasto;
    private Mapa mapa;

    // [tempoAtendMin, tempoAtendMax[
    public Cliente(Localizacao localizacao, int tempoAtendMin, int tempoAtendMax, Mapa mapa, String imagem) {
        super(localizacao, imagem);
        this.tempoGasto = 0;
        estado = Estado.fora;
        this.mapa = mapa;
        Random random = new Random();
        this.tempoAtend = random.nextInt(tempoAtendMax-tempoAtendMin) + tempoAtendMin;
        tempoGasto = 0;
    }

    public Localizacao getLocalizacaoDestino() {
        return localizacaoDestino;
    }

    public void setLocalizacaoAtual(Localizacao localizacaoAtual) {
        super.setLocalizacaoAtual(localizacaoAtual);
    }

    public void setLocalizacaoDestino(Localizacao localizacaoDestino) {
        this.localizacaoDestino = localizacaoDestino;
    }

    public void proximoDestino() {
        Localizacao localizacao;
        int x;
        int y;
        switch (estado) {
            case Estado.fora:
                localizacao = verEntrada();
                x = localizacao.getX();
                y = localizacao.getY();
                localizacaoDestino = new Localizacao(x, y-1);
                atualizarEstado();
                break;
            case Estado.dentro:
                localizacao = verMelhorCaixa();
                x = localizacao.getX();
                y = localizacao.getY();
                localizacaoDestino = new Localizacao(x, y+1);
                atualizarEstado();
                break;
            case Estado.atendendo:
                atualizarEstado();
                tempoGasto++;
                break;
            case Estado.atendido:
                desviarDoCaixa();
                atualizarEstado();
                break;
            case Estado.saindo:
                localizacaoDestino = verSaida();
                atualizarEstado();
                break;
            default:
                break;
        }
    }
    
    public void executarAcao(){
        Localizacao destino = getLocalizacaoDestino();
        if(destino != null){
            if (localizacaoDestino.equals(getLocalizacaoAtual())) {
                proximoDestino();                    
            }
            Localizacao proximaLocalizacao = getLocalizacaoAtual().proximaLocalizacao(localizacaoDestino);
            if(mapa.getItem(proximaLocalizacao.getX(), proximaLocalizacao.getY()) == null)
                setLocalizacaoAtual(proximaLocalizacao);
        }
    }

    private void atualizarEstado() {
        switch (estado) {
            case Estado.fora:
                estado = Estado.dentro;
                break;
            case Estado.dentro:
                estado = Estado.atendendo;
                break;
            case Estado.atendendo:
                if (tempoAtend == tempoGasto){
                    estado = Estado.atendido;
                }
                break;
            case Estado.atendido:
                estado = Estado.saindo;
                break;
            case Estado.saindo:
                if (getLocalizacaoAtual() == localizacaoDestino) {
                    estado = Estado.sair;
                }
                break;
            default:
                break;
        }
    }

    public Mapa getMapa() {
        return mapa;
    }

    public Estado getEstado() {
        return estado;
    }

    public abstract Localizacao verMelhorCaixa();

    public abstract Localizacao verEntrada();

    public abstract Localizacao verSaida();

    public abstract void desviarDoCaixa();
}
