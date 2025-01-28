import java.awt.Image;
import javax.swing.ImageIcon;

public abstract class Elemento {
    private Localizacao localizacaoAtual;
    private Image imagem;

    public Elemento(Localizacao localizacaoAtual, String imagem) {
        this.localizacaoAtual = localizacaoAtual;
        localizacaoAtual = null;
        this.imagem = new ImageIcon(getClass().getResource("Imagens/" + imagem + ".png")).getImage();
    }

    public Localizacao getLocalizacaoAtual() {
        return localizacaoAtual;
    }

    protected void setLocalizacaoAtual(Localizacao localizacaoAtual) {
        this.localizacaoAtual = localizacaoAtual;
    }
    
    public Image getImagem(){
        return imagem;
    }
}