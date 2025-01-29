public class ClientePreferencial extends Cliente {
    ClientePreferencial(Localizacao localizacao, int tempoAtendMin, int tempoAtendMax, Simulacao simulacao) {
        super(localizacao, tempoAtendMin, tempoAtendMax, simulacao, "clientepreferencial");
    }

    @Override
    public Caixa verCaixa() {
        return getSimulacao().getCaixa(TipoAtendimento.Preferencial);
    }

    @Override
    public Localizacao verEntrada() {
        return getSimulacao().getMapa().getEntrada(TipoAtendimento.Preferencial);
    }

    @Override
    public Localizacao verSaida() {
        return getSimulacao().getMapa().getSaida(TipoAtendimento.Preferencial);
    }
}
