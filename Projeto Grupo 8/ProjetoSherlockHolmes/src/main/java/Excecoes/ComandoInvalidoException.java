package Excecoes;

/**
 * Lançada quando o interpretador não consegue reconhecer a palavra-chave
 * introduzida pelo utilizador.
 *
 * @author Grupo 8
 * @version 1.0
 */
public class ComandoInvalidoException extends JogoException {

    /**
     * Cria a exceção informando qual foi o comando desconhecido.
     *
     * @param comando A palavra-chave que o sistema não reconheceu.
     */
    public ComandoInvalidoException(String comando) {
        super("O comando '" + comando + "' nao e valido. Tenta 'ajuda'.");
    }
}