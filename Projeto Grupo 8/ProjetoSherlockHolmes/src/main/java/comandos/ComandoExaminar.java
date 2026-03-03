package comandos;
import nucleo.Jogo;

/**
 * Permite ler os detalhes de uma pista específica que o jogador já tenha recolhido
 * e armazenado no inventário.
 *
 * @author Grupo 8
 */
public class ComandoExaminar implements iComando {

    /**
     * Procura uma pista no inventário pelo nome e devolve a sua descrição detalhada.
     *
     * @param j    A instância atual do jogo.
     * @param args O nome da pista a examinar.
     * @return A descrição da pista ou erro se não existir.
     */
    public String executar(Jogo j, String[] args) {
        if (args.length == 0) return "Examinar qual pista?";
        return j.obterJogador().examinarPista(String.join(" ", args));
    }
}