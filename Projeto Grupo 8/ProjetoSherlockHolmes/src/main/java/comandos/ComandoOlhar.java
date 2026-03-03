package comandos;
import nucleo.Jogo;

/**
 * Fornece a descrição completa do local atual, incluindo saídas disponíveis e entidades visíveis.
 *
 * @author Grupo 8
 */
public class ComandoOlhar implements iComando {

    /**
     * Devolve a descrição do ambiente.
     *
     * @param j    A instância atual do jogo.
     * @param args Argumentos (não utilizados).
     * @return Descrição do local atual.
     */
    public String executar(Jogo j, String[] args) {
        return j.obterJogador().olhar();
    }
}