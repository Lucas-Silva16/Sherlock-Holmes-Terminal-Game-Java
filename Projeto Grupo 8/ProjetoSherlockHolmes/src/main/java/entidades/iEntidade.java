package entidades;

import nucleo.Jogador;

/**
 * Define o comportamento básico de qualquer objeto interativo no mundo do jogo.
 * Tanto NPCs como Objetos (itens) implementam esta interface.
 *
 * @author Grupo 8
 */
public interface iEntidade {

    /**
     * Devolve o nome da entidade.
     *
     * @return O nome identificador.
     */
    String obterNome();

    /**
     * Devolve a descrição base da entidade.
     *
     * @return Texto descritivo.
     */
    String obterDescricao();

    /**
     * Define como a entidade reage ao ser examinada detalhadamente pelo jogador.
     *
     * @param j O jogador que realiza a inspeção.
     * @return O resultado da inspeção.
     */
    String inspecionar(Jogador j);
}