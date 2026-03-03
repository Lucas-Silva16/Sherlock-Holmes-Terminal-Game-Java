package comandos;
import nucleo.Jogo;

/**
 * Implementa o comando responsável por listar todas as ações disponíveis para o jogador.
 * Serve como guia de referência rápida durante a execução do jogo.
 *
 * @author Grupo 8
 */
public class ComandoAjuda implements iComando {

    /**
     * Devolve uma String formatada contendo a lista de comandos e exemplos de utilização.
     *
     * @param j    A instância atual do jogo (não utilizada neste comando).
     * @param args Argumentos do comando (não utilizados).
     * @return O menu de ajuda textual.
     */
    public String executar(Jogo j, String[] args) {
        return "================================================\n" +
                "\n" +
                "--- COMANDOS DISPONIVEIS ---\n" +
                " > ir [nome do local]    : Mover-se (Ex: 'ir Mercado dos Lavradores')\n" +
                " > olhar                 : Ver o que esta a tua volta\n" +
                " > falar [nome]          : Conversar com um personagem\n" +
                " > dar [item] [nome]     : Entregar um item a alguem\n" +
                " > usar [item]           : Usar um item do inventario(Ex:Podes beber ou ler)\n" +
                " > inspecionar [nome]    : Observar detalhes e as pessoas / Apanhar itens\n" +
                " > digitar [codigo]      : Inserir codigos em paineis/cofres\n" +
                " > inventario            : Ver itens e dinheiro\n" +
                " > pistas                : Ver o teu caderno de notas\n" +
                " > deduzir               : Tentar resolver o misterio do nivel(Depois de teres as pistas todas)\n" +
                " > ajuda                 : Listar todos os comandos novamente\n" +
                " > sair                  : Fechar o jogo\n" +
                "----------------------------------------------------------------------------------";
    }
}