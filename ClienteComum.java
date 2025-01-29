public class ClienteComum extends Cliente {
    ClienteComum(Localizacao localizacao, int tempoAtendMin, int tempoAtendMax, Simulacao simulacao) {
        super(localizacao, tempoAtendMin, tempoAtendMax, simulacao, "clientecomum");
    }

    @Override
    public Caixa verCaixa() {
        return getSimulacao().getCaixa(TipoAtendimento.Comum);
    }

    @Override
    public Localizacao verEntrada() {
        return getSimulacao().getMapa().getEntrada(TipoAtendimento.Comum);
    }

    @Override
    public Localizacao verSaida() {
        return getSimulacao().getMapa().getSaida(TipoAtendimento.Comum);
    }
}
