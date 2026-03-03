package comandos;
import nucleo.Jogo;

/**
 * Lista o conteúdo da mochila do jogador e o saldo monetário atual.
 *
 * @author Grupo 8
 */
public class ComandoInventario implements iComando {

    /**
     * Obtém a representação textual do inventário.
     *
     * @param j    A instância atual do jogo.
     * @param args Argumentos (não utilizados).
     * @return Lista formatada de itens e dinheiro.
     */
    public String executar(Jogo j, String[] args) {
        return j.obterJogador().obterInventario().listarItens();
    }
}