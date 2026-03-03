package comandos;
import nucleo.Jogo;

/**
 * Realiza a movimentação do jogador entre locais conectados no mundo do jogo.
 *
 * @author Grupo 8
 */
public class ComandoIr implements iComando {

    /**
     * Tenta mover o jogador para um novo local baseado no nome ou direção fornecida.
     *
     * @param j    A instância atual do jogo.
     * @param args O nome do destino (ex: "Mercado", "Norte").
     * @return Descrição do novo local ou mensagem de erro se o caminho não existir.
     */
    public String executar(Jogo j, String[] args) {
        if (args.length == 0) return "Ir para onde?";
        return j.obterJogador().mover(String.join(" ", args));
    }
}