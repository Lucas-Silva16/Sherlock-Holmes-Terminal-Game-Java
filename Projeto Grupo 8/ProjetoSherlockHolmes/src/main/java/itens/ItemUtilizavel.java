package itens;

import nucleo.Jogador;

/**
 * Representa um item que, além de ser colecionável, possui uma função ativa quando usado.
 * Utiliza o padrão Strategy (via iEfeitoItem) para delegar o comportamento do uso.
 *
 * @author Grupo 8
 */
public class ItemUtilizavel extends ItemColecionavel implements iUtilizavel {
    
    /**
     * A estratégia que define o que acontece quando o item é usado.
     */
    private iEfeitoItem efeito;

    /**
     * Constrói um item utilizável com um efeito específico.
     *
     * @param nome      O nome do item.
     * @param descricao A descrição do item.
     * @param efeito    A implementação da lógica de uso (Strategy).
     */
    public ItemUtilizavel(String nome, String descricao, iEfeitoItem efeito) {
        super(nome, descricao);
        this.efeito = efeito;
    }
    
    /**
     * Constrói um item utilizável com um efeito padrão (sem ação especial).
     * Útil para itens que o jogador pode tentar usar, mas que não fazem nada de complexo.
     *
     * @param nome      O nome do item.
     * @param descricao A descrição do item.
     */
    public ItemUtilizavel(String nome, String descricao) {
        super(nome, descricao);
        // Lambda padrão para itens sem efeito específico
        this.efeito = (j) -> "Usas " + nome + ", mas nada acontece.";
    }

    /**
     * Executa o efeito associado a este item.
     *
     * @param j O jogador que usa o item.
     * @return O resultado da execução do efeito.
     */
    @Override
    public String usar(Jogador j) {
        return efeito.executar(j);
    }
}