package itens;
import nucleo.Jogador;

/**
 * Interface funcional que implementa o padrão Strategy.
 * Define a lógica específica (o "efeito") que ocorre quando um item é utilizado.
 * Isto permite separar a classe do item da sua lógica de execução.
 *
 * @author Grupo 8
 */
public interface iEfeitoItem {
    
    /**
     * Executa a lógica do efeito do item.
     *
     * @param j O jogador que invocou o uso do item.
     * @return O resultado textual da ação.
     */
    String executar(Jogador j);
}