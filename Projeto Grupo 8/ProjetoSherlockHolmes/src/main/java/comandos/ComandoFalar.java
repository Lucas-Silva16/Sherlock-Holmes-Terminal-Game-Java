package comandos;
import nucleo.Jogo;

/**
 * Inicia ou continua um diálogo com um NPC presente no local atual.
 *
 * @author Grupo 8
 */
public class ComandoFalar implements iComando {

    /**
     * Procura o NPC no local atual e obtém a próxima fala do seu gestor de diálogos.
     *
     * @param j    A instância atual do jogo.
     * @param args O nome do NPC com quem falar.
     * @return A fala do NPC ou mensagem de erro se o NPC não estiver presente.
     */
    public String executar(Jogo j, String[] args) {
        if (args.length == 0) return "Falar com quem?";
        return j.obterJogador().falar(String.join(" ", args));
    }
}