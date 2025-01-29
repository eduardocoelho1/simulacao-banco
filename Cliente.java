/**
 * Representa os veiculos da simulacao.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Cliente extends Elemento {
    private Localizacao localizacaoDestino;
    private boolean atendimento;
    private int velAtend;

    public Cliente(Localizacao localizacao, int velAtend) {
        super(localizacao, "clientecomum");
        this.velAtend = velAtend;
        atendimento = false;
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
    
    public void executarAcao(){
        Localizacao destino = getLocalizacaoDestino();
        if(destino != null){
            Localizacao proximaLocalizacao = getLocalizacaoAtual().proximaLocalizacao(localizacaoDestino);
            setLocalizacaoAtual(proximaLocalizacao);
        }
    } 
}
