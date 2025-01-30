import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Representa os elementos do mapa.
 */
public abstract class Elemento {
    private Localizacao localizacaoAtual;
    private Image imagem;

    /**
     * @param localizacaoAtual: localização inicial do elemento.
     * @param imagem: nome de um arquivo de imagem png (sem a extensão).
     */
    public Elemento(Localizacao localizacaoAtual, String imagem) {
        this.localizacaoAtual = localizacaoAtual;
        localizacaoAtual = null;
        this.imagem = new ImageIcon(getClass().getResource("Imagens/" + imagem + ".png")).getImage();
    }

    public Localizacao getLocalizacaoAtual() {
        return localizacaoAtual;
    }

    public void setLocalizacaoAtual(Localizacao localizacaoAtual) {
        this.localizacaoAtual = localizacaoAtual;
    }
    
    public Image getImagem(){
        return imagem;
    }
}