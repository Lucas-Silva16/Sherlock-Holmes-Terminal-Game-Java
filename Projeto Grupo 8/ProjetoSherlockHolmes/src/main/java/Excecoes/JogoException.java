package Excecoes;

/**
 * Representa uma exceção genérica relacionada com a lógica do jogo.
 * Serve como base para outras exceções específicas do domínio do jogo.
 *
 * @author Grupo 8
 * @version 1.0
 */
public class JogoException extends Exception {

    /**
     * Constrói uma nova exceção com uma mensagem detalhada.
     *
     * @param mensagem A mensagem que descreve o erro ocorrido.
     */
    public JogoException(String mensagem) {
        super(mensagem);
    }
}