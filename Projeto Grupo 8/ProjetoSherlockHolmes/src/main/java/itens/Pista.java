package itens;

/**
 * Representa uma informação ou conhecimento adquirido pelo jogador.
 * As pistas são guardadas numa secção especial do inventário e são essenciais
 * para resolver os mistérios e avançar de nível.
 *
 * @author Grupo 8
 */
public class Pista implements iItem {
    private String nome;
    private String informacao;

    /**
     * Cria uma nova pista.
     *
     * @param nome       O título da pista (ex: "Bilhete Rasgado").
     * @param informacao O conteúdo detalhado da pista.
     */
    public Pista(String nome, String informacao) {
        this.nome = nome;
        this.informacao = informacao;
    }

    @Override
    public String obterNome() { return nome; }
    
    /**
     * Devolve a descrição formatada da pista.
     *
     * @return String contendo a informação da pista.
     */
    @Override
    public String obterDescricao() { return "Uma pista importante: " + informacao; }
    
    /**
     * Obtém a informação bruta da pista sem formatação adicional.
     *
     * @return O conteúdo da pista.
     */
    public String obterInformacao() { return informacao; }
}