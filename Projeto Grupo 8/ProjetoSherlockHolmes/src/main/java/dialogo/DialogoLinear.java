package dialogo;

import java.util.List;

/**
 * Implementa um sistema de diálogo sequencial simples.
 * As falas são apresentadas uma a uma, sempre na mesma ordem, até se esgotarem.
 *
 * @author Grupo 8
 */
public class DialogoLinear implements iDialogo {

    /**
     * Lista de Strings onde cada elemento é uma fala do NPC.
     */
    private List<String> falas;

    /**
     * Índice que controla qual a fala atual a ser exibida.
     */
    private int indice = 0;
    
    /**
     * Constrói um novo diálogo linear com uma lista pré-definida de falas.
     *
     * @param falas Lista ordenada das frases que o NPC irá dizer.
     */
    public DialogoLinear(List<String> falas) {
        this.falas = falas;
    }
    
    /**
     * Devolve a próxima fala da lista e avança o índice.
     * Se a lista já tiver sido toda lida, devolve uma mensagem padrão.
     *
     * @return A próxima fala ou mensagem de fim de conversa.
     */
    @Override
    public String obterProximaFala() {
        // Se já lemos todas as falas, retorna a mensagem padrão
        if (terminou()) {
            return "Nao tenho mais nada a dizer.";
        }
        
        // Obtém a fala atual e avança o índice
        String fala = falas.get(indice);
        indice++;
        return fala;
    }

    /**
     * Verifica se o índice já ultrapassou o número total de falas disponíveis.
     *
     * @return true se todas as falas já foram lidas.
     */
    @Override
    public boolean terminou() {
        return indice >= falas.size();
    }
}