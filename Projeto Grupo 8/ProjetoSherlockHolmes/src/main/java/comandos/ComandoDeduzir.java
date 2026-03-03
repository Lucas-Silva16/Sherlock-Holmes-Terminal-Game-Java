package comandos;
import nucleo.Jogo;

/**
 * Implementa a mecânica de dedução para avançar de nível.
 * Verifica se o jogador reuniu as pistas necessárias para resolver o mistério atual
 * e desbloquear novas áreas.
 *
 * @author Grupo 8
 */
public class ComandoDeduzir implements iComando {

    /**
     * Aciona a verificação de progresso do nível atual.
     *
     * @param j    A instância atual do jogo.
     * @param args Argumentos do comando (geralmente vazios).
     * @return O resultado da tentativa de dedução (sucesso ou dica do que falta).
     */
    public String executar(Jogo j, String[] args) {
        return j.resolverPuzzleNivel();
    }
}