package mecanicas;
import nucleo.Local;

/**
 * Interface que define o comportamento de qualquer mecanismo interativo num local
 * (ex: teclados numéricos, alavancas, fechaduras eletrónicas).
 * Permite adicionar lógica de "puzzle" a um Local específico.
 *
 * @author Grupo 8
 */
public interface iMecanismo {

    /**
     * Tenta resolver o mecanismo com base na entrada do jogador.
     *
     * @param input      O texto digitado pelo jogador (ex: um código numérico).
     * @param localAtual O local onde o mecanismo se encontra (para poder alterar saídas, se necessário).
     * @return true se o mecanismo foi ativado/resolvido com sucesso, false caso contrário.
     */
    boolean tentarResolver(String input, Local localAtual);
}