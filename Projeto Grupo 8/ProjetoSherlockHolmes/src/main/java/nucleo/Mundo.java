package nucleo;
import java.util.HashMap;
import java.util.Map;

/**
 * Registo central de todos os locais existentes no jogo.
 * Serve para armazenar e recuperar referências para locais através do seu nome.
 *
 * @author Grupo 8
 */
public class Mundo {
    private Map<String, Local> locais = new HashMap<>();
    private Local localInicial;

    /**
     * Regista um local no mundo.
     * @param chave Nome único/chave do local.
     * @param local A instância do local.
     */
    public void adicionarLocal(String chave, Local local) { locais.put(chave.toLowerCase(), local); }
    
    /**
     * Recupera um local pelo nome.
     * @param chave Nome do local.
     * @return O objeto Local ou null se não existir.
     */
    public Local getLocal(String chave) { return locais.get(chave.toLowerCase()); }
    
    public void setLocalInicial(Local l) { this.localInicial = l; }
    public Local getLocalInicial() { return localInicial; }
}