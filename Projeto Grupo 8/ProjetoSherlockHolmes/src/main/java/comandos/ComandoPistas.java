package comandos;
import nucleo.Jogo;

/**
 * Lista todas as pistas importantes que o jogador já anotou no seu caderno.
 *
 * @author Grupo 8
 */
public class ComandoPistas implements iComando {

    /**
     * Obtém a lista formatada de pistas descobertas.
     *
     * @param j    A instância atual do jogo.
     * @param args Argumentos (não utilizados).
     * @return Lista de pistas.
     */
    public String executar(Jogo j, String[] args) {
        return j.obterJogador().obterInventario().listarPistas();
    }
}