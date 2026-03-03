package nucleo;

import java.util.*;
import entidades.iEntidade;
import mecanicas.iMecanismo;

/**
 * Representa um espaço físico no mundo do jogo (ex: uma sala, uma rua, um porto).
 * Cada local pode conter saídas para outros locais, entidades (NPCs/Objetos) e mecanismos.
 *
 * @author Grupo 8
 */
public class Local {
    private String nome;
    private String descricao;
    private Map<String, Local> saidas;
    private Map<String, iEntidade> entidades;
    private List<iMecanismo> mecanismos; 

    /**
     * Cria um novo local.
     * @param nome Nome do local (ex: "Zona Velha").
     * @param descricao Descrição atmosférica do local.
     */
    public Local(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
        this.saidas = new HashMap<>();
        this.entidades = new HashMap<>();
        this.mecanismos = new ArrayList<>();
    }

    /**
     * Adiciona um mecanismo interativo (ex: cofre) a este local.
     * @param m O mecanismo a adicionar.
     */
    public void adicionarMecanismo(iMecanismo m) { mecanismos.add(m); }

    /**
     * Tenta ativar todos os mecanismos presentes no local com um input.
     * Utilizado para o comando "digitar".
     *
     * @param input O código ou texto introduzido pelo jogador.
     * @return true se algum mecanismo foi ativado com sucesso.
     */
    public boolean ativarMecanismos(String input) {
        for (iMecanismo m : mecanismos) {
            if (m.tentarResolver(input, this)) return true;
        }
        return false;
    }

    public void adicionarSaida(String direcao, Local local) { saidas.put(direcao.toLowerCase(), local); }
    public void adicionarEntidade(iEntidade entidade) { entidades.put(entidade.obterNome().toLowerCase(), entidade); }
    public void removerEntidade(String nome) { entidades.remove(nome.toLowerCase()); }
    
    public Local obterSaida(String direcao) { 
        if (saidas.containsKey(direcao.toLowerCase())) return saidas.get(direcao.toLowerCase());
        for (Local l : saidas.values()) if (l.obterNome().equalsIgnoreCase(direcao)) return l;
        return null;
    }
    
    public iEntidade obterEntidade(String nome) { return entidades.get(nome.toLowerCase()); }
    public String obterNome() { return nome; }
    
    /**
     * Gera a descrição completa do local para o jogador, incluindo entidades visíveis e saídas.
     * @return Texto formatado.
     */
    public String obterDescricaoCompleta() {
        StringBuilder sb = new StringBuilder("\n=== " + nome + " ===\n" + descricao + "\n");
        if (!entidades.isEmpty()) {
            sb.append("Tu ves: ");
            for (iEntidade e : entidades.values()) sb.append("[").append(e.obterNome()).append("] ");
            sb.append("\n");
        }
        sb.append("Saidas: ");
        Set<String> destinos = new HashSet<>();
        StringBuilder saidasStr = new StringBuilder();
        for (Local l : saidas.values()) {
            if (destinos.add(l.obterNome())) {
                if (saidasStr.length() > 0) saidasStr.append(" || ");
                saidasStr.append(l.obterNome());
            }
        }
        sb.append(saidasStr);
        return sb.toString();
    }
}