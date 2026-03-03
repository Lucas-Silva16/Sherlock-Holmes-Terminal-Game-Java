package itens;

import entidades.Objeto;

/**
 * Representa um objeto que pode ser recolhido e guardado no inventário do jogador.
 * Herda as características base de um Objeto (nome e descrição).
 *
 * @author Grupo 8
 */
public class ItemColecionavel extends Objeto implements iItem {
    
    /**
     * Constrói um novo item colecionável.
     * Define automaticamente a propriedade 'podeSerColecionado' como verdadeira.
     *
     * @param nome      O nome do item.
     * @param descricao A descrição visual do item.
     */
    public ItemColecionavel(String nome, String descricao) {
        super(nome, descricao, true);
    }
}