package comandos;
import nucleo.Jogo;

/**
 * Implementa a lógica para encerrar o jogo voluntariamente.
 *
 * @author Grupo 8
 */
public class ComandoSair implements iComando {

    /**
     * Devolve a mensagem de despedida e sinaliza o encerramento do ciclo do jogo.
     *
     * @param j    A instância atual do jogo.
     * @param args Argumentos (não utilizados).
     * @return Mensagem de encerramento.
     */
    public String executar(Jogo j, String[] args) {
        return "Saindo...";
    }
}