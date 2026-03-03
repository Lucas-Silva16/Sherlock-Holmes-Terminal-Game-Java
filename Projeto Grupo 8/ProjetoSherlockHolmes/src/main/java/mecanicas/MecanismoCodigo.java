package mecanicas;

import nucleo.Local;

/**
 * Implementa um mecanismo de segurança baseado num código (senha).
 * Geralmente utilizado para destrancar portas ou abrir cofres.
 *
 * @author Grupo 8
 */
public class MecanismoCodigo implements iMecanismo {
    private String codigoCorreto;
    private String mensagemSucesso;
    private String mensagemErro;
    
    /**
     * O local que ficará acessível após o código ser inserido corretamente.
     */
    private Local destino;
    
    /**
     * O nome da direção/saída que será criada (ex: "Armazem 4").
     */
    private String nomeDirecao;

    /**
     * Cria um novo mecanismo de código.
     *
     * @param codigo     A string exata que o jogador deve digitar.
     * @param msgSucesso A mensagem exibida ao acertar.
     * @param msgErro    A mensagem exibida ao falhar.
     * @param destino    O Local de destino que será desbloqueado (pode ser null se for apenas um cofre).
     * @param direcao    O nome da saída para o novo local.
     */
    public MecanismoCodigo(String codigo, String msgSucesso, String msgErro, Local destino, String direcao) {
        this.codigoCorreto = codigo;
        this.mensagemSucesso = msgSucesso;
        this.mensagemErro = msgErro;
        this.destino = destino;
        this.nomeDirecao = direcao;
    }

    /**
     * Verifica se o input do jogador contém o código correto.
     * Se contiver, desbloqueia o acesso ao novo local e exibe a mensagem de sucesso.
     *
     * @param input      O texto introduzido pelo jogador.
     * @param localAtual O local onde a saída será adicionada.
     * @return true se o código estava correto.
     */
    @Override
    public boolean tentarResolver(String input, Local localAtual) {
        if (input.contains(codigoCorreto)) {
            System.out.println(mensagemSucesso);
            if (destino != null) {
                // Adiciona a saída com o nome original e em minúsculas para facilitar
                localAtual.adicionarSaida(nomeDirecao, destino);
                localAtual.adicionarSaida(nomeDirecao.toLowerCase(), destino);
            }
            return true;
        }
        System.out.println(mensagemErro);
        return false;
    }
}