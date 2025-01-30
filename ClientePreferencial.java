/**
 * Representa os clientes preferenciais da simulação.
 */
public class ClientePreferencial extends Cliente {
    /**
     * @param localizacao: localização onde o cliente será criado.
     * @param tempoAtendMin: tempo mínimo de atendimento.
     * @param tempoAtendMax: tempo máximo de atendimeno.
     * @param mapa: mapa da simulação.
     */
    ClientePreferencial(Localizacao localizacao, int tempoAtendMin, int tempoAtendMax, Mapa mapa) {
        super(localizacao, tempoAtendMin, tempoAtendMax, mapa, "clientepreferencial");
    }

    @Override
    public Localizacao verMelhorCaixa() {
        return getMapa().getMelhorCaixa(TipoAtendimento.Preferencial);
    }

    @Override
    public Localizacao verEntrada() {
        return getMapa().getEntrada(TipoAtendimento.Preferencial);
    }

    @Override
    public Localizacao verSaida() {
        return getMapa().getSaida(TipoAtendimento.Preferencial);
    }

    @Override
    public void desviarDoCaixa() {
        Localizacao localizacao = getLocalizacaoAtual();
        int x = localizacao.getX()-1;
        int y = localizacao.getY()-2;
        setLocalizacaoDestino(new Localizacao(x, y));
    }
}
