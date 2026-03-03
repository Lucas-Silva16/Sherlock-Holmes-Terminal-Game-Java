package itens;

/**
 * Define o contrato básico para qualquer item no jogo.
 * Garante que todos os itens possuem um nome e uma descrição acessível.
 *
 * @author Grupo 8
 */
public interface iItem {
    
    /**
     * Obtém o nome do item.
     *
     * @return O nome identificador do item.
     */
    String obterNome();

    /**
     * Obtém a descrição do item.
     *
     * @return Texto descritivo do item.
     */
    String obterDescricao();
}