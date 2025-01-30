/**
 * Representa os clientes comuns da simulação.
 */
public class ClienteComum extends Cliente {
    /**
     * @param localizacao: localização onde o cliente será criado.
     * @param tempoAtendMin: tempo mínimo de atendimento.
     * @param tempoAtendMax: tempo máximo de atendimeno.
     * @param mapa: mapa da simulação.
     */
    ClienteComum(Localizacao localizacao, int tempoAtendMin, int tempoAtendMax, Mapa mapa) {
        super(localizacao, tempoAtendMin, tempoAtendMax, mapa, "clientecomum");
    }

    @Override
    public Localizacao verMelhorCaixa() {
        return getMapa().getMelhorCaixa(TipoAtendimento.Comum);
    }

    @Override
    public Localizacao verEntrada() {
        return getMapa().getEntrada(TipoAtendimento.Comum);
    }

    @Override
    public Localizacao verSaida() {
        return getMapa().getSaida(TipoAtendimento.Comum);
    }

    @Override
    public void desviarDoCaixa() {
        Localizacao localizacao = getLocalizacaoAtual();
        int x = localizacao.getX()+1;
        int y = localizacao.getY()-2;
        setLocalizacaoDestino(new Localizacao(x, y));
    }
}
