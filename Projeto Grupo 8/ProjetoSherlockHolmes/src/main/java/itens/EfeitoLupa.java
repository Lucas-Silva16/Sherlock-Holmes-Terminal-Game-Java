package itens;
import nucleo.Jogador;

/**
 * Implementa a lógica de uso da Lupa.
 * Serve como ferramenta auxiliar para o comando 'inspecionar'.
 *
 * @author Grupo 8
 */
public class EfeitoLupa implements iEfeitoItem {
    
    /**
     * Informa o jogador de que a lupa é utilizada automaticamente através de outro comando.
     *
     * @param j O jogador.
     * @return Instrução de uso.
     */
    @Override
    public String executar(Jogador j) {
        return "A lente esta suja, mas serve. Use 'inspecionar [algo]' para a usar.";
    }
}