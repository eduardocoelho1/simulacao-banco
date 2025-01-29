/**
 * Representa os veiculos da simulacao.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public abstract class Cliente extends Elemento {
    private enum Estado {
        fora,
        dentro,
        atendendo,
        atendido
    }

    private Localizacao localizacaoDestino;
    private Estado estado;
    private int tempoAtend;
    private int tempoGasto = 0;
    private Simulacao simulacao;

    public Cliente(Localizacao localizacao, int tempoAtend, Simulacao simulacao, String imagem) {
        super(localizacao, imagem);
        this.tempoAtend = tempoAtend;
        this.tempoGasto = 0;
        estado = Estado.fora;
        this.simulacao = simulacao;
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
                trocarEstado();
                break;
            case Estado.dentro:
                localizacao = verCaixa().getLocalizacaoAtual();
                x = localizacao.getX();
                y = localizacao.getY();
                localizacaoDestino = new Localizacao(x, y+1);
                trocarEstado();
                break;
            case Estado.atendido:
            
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
            setLocalizacaoAtual(proximaLocalizacao);
        }
    }

    private void trocarEstado() {
        switch (estado) {
            case Estado.fora:
                estado = Estado.dentro;
                break;
            case Estado.dentro:
                estado = Estado.atendendo;
                break;
            case Estado.atendendo:
                estado = Estado.atendido;
                break;
            default:
                break;
        }
    }

    public Simulacao getSimulacao() {
        return simulacao;
    }

    public abstract Caixa verCaixa();

    public abstract Localizacao verEntrada();

    public abstract Localizacao verSaida();
}
