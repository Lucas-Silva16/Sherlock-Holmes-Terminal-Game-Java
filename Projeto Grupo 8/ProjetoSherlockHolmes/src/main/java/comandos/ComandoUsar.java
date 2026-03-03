package comandos;
import nucleo.Jogo;

/**
 * Ativa o efeito especial de um item utilizável (ex: beber poncha, ler jornal).
 *
 * @author Grupo 8
 */
public class ComandoUsar implements iComando {

    /**
     * Executa a lógica específica associada ao item (definida via Strategy/Lambda).
     *
     * @param j    A instância atual do jogo.
     * @param args O nome do item a usar.
     * @return O resultado da utilização do item.
     */
    public String executar(Jogo j, String[] args) {
        if (args.length == 0) return "Usar o quê?";
        return j.obterJogador().usarItem(String.join(" ", args));
    }
}