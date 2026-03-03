package itens;

import nucleo.Jogador;

/**
 * Define o comportamento para itens que podem ser ativamente usados pelo jogador
 * (ex: beber uma poção, ler um jornal, usar uma chave).
 *
 * @author Grupo 8
 */
public interface iUtilizavel {
    
    /**
     * Aciona o comportamento específico do item sobre o jogador ou o mundo.
     *
     * @param j O jogador que está a usar o item.
     * @return Uma mensagem descrevendo o resultado da ação.
     */
    String usar(Jogador j);
}