public class ClientePreferencial extends Cliente {
    ClientePreferencial(Localizacao localizacao, int velAtend, Simulacao simulacao) {
        super(localizacao, velAtend, simulacao, "clientepreferencial");
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
