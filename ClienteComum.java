public class ClienteComum extends Cliente {
    ClienteComum(Localizacao localizacao, int velAtend, Simulacao simulacao) {
        super(localizacao, velAtend, simulacao, "clientecomum");
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
