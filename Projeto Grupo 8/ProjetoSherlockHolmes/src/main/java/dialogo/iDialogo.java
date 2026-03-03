package dialogo;

/**
 * Define o contrato para qualquer sistema de conversação no jogo.
 * Permite que diferentes tipos de diálogo (linear, ramificado, aleatório) sejam
 * utilizados pelos NPCs de forma uniforme.
 *
 * @author Grupo 8
 */
public interface iDialogo {

    /**
     * Obtém a próxima linha de texto disponível na sequência do diálogo.
     *
     * @return A String contendo a fala do personagem.
     */
    String obterProximaFala();

    /**
     * Verifica se o diálogo chegou ao fim da sua sequência de falas.
     *
     * @return true se não houver mais falas novas, false caso contrário.
     */
    boolean terminou();
}