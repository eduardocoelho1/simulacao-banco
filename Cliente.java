import java.util.Random;

/**
 * Representa os clientes da simulação.
 */
public abstract class Cliente extends Elemento {
    /**
     * Representa os estados possíveis para o cliente.
     */
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

    /**
     * @param localizacao: localização onde o cliente será criado.
     * @param tempoAtendMin: tempo mínimo de atendimento.
     * @param tempoAtendMax: tempo máximo de atendimeno.
     * @param mapa: mapa da simulação.
     * @param imagem: nome de um arquivo de imagem png (sem a extensão).
     */
    public Cliente(Localizacao localizacao, int tempoAtendMin, int tempoAtendMax, Mapa mapa, String imagem) {
        super(localizacao, imagem);
        localizacaoDestino = localizacao;
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

    /**
     * Encontra o próximo destino com base no estado.
     */
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
    
    /**
     * Faz o cliente executar uma ação.
     */
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

    /**
     * Realiza transições de estado.
     */
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

    /**
     * @return mapa da simulação.
     */
    public Mapa getMapa() {
        return mapa;
    }

    /**
     * @return estado atual do cliente.
     */
    public Estado getEstado() {
        return estado;
    }

    /**
     * Descobre o caixa com menos pessoas na fila.
     * @return a localização do melhor caixa com prioridade para os mais próximos da parede lateral.
     */
    public abstract Localizacao verMelhorCaixa();

    /**
     * Descobre a entrada.
     * @return a localização da entrada.
     */
    public abstract Localizacao verEntrada();

    /**
     * Descobre a saída.
     * @return a localização da saída.
     */
    public abstract Localizacao verSaida();

    /**
     * Cliente sai do caixa pela lateral.
     */
    public abstract void desviarDoCaixa();
}
