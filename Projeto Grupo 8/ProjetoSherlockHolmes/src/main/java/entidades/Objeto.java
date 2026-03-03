package entidades;

import nucleo.Jogador;

/**
 * Representa um objeto inanimado presente num local do jogo.
 * Pode ser um item decorativo ou algo colecionável.
 *
 * @author Grupo 8
 */
public class Objeto implements iEntidade {
    private String nome;
    private String descricao;
    
    /**
     * Indica se este objeto pode ser colocado no inventário.
     */
    protected boolean podeSerColecionado;

    /**
     * Cria um novo objeto.
     *
     * @param nome               O nome visível no jogo.
     * @param descricao          A descrição ao olhar.
     * @param podeSerColecionado Define se é um item ou apenas cenário.
     */
    public Objeto(String nome, String descricao, boolean podeSerColecionado) {
        this.nome = nome;
        this.descricao = descricao;
        this.podeSerColecionado = podeSerColecionado;
    }

    @Override
    public String obterNome() {
        return nome;
    }

    @Override
    public String obterDescricao() {
        return descricao;
    }

    @Override
    public String inspecionar(Jogador j) {
        return descricao;
    }
}