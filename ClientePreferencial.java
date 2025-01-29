public class ClientePreferencial extends Cliente {
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
