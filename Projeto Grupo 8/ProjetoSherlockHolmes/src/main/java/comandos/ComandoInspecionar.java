package comandos;
import nucleo.Jogo;

/**
 * Permite ao jogador observar detalhes de entidades no local ou apanhar itens colecionáveis.
 * É o comando principal para interagir com o cenário.
 *
 * @author Grupo 8
 */
public class ComandoInspecionar implements iComando {

    /**
     * Executa a inspeção. Se o alvo for um item, ele é adicionado ao inventário.
     * Se for um NPC ou objeto fixo, revela detalhes extra.
     *
     * @param j    A instância atual do jogo.
     * @param args O nome da entidade a inspecionar.
     * @return A descrição detalhada ou confirmação de item recolhido.
     */
    public String executar(Jogo j, String[] args) {
        if (args.length == 0) return "Inspecionar o quê?";
        return j.obterJogador().inspecionar(String.join(" ", args));
    }
}