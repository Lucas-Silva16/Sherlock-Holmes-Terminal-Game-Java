package nucleo;

import entidades.NPC;
import entidades.iEntidade;
import itens.ItemColecionavel;
import itens.Pista;
import itens.iUtilizavel;

/**
 * Representa o avatar do utilizador no mundo do jogo.
 * Mantém o estado atual do jogador (localização) e o acesso ao seu inventário.
 * Centraliza as ações principais como mover, falar e inspecionar.
 *
 * @author Grupo 8
 */
public class Jogador {
    private Local localAtual;
    private Inventario inventario;

    /**
     * Cria um novo jogador na posição inicial definida.
     * @param localInicial O ponto de partida do jogo.
     */
    public Jogador(Local localInicial) {
        this.localAtual = localInicial;
        this.inventario = new Inventario();
    }

    /**
     * Tenta deslocar o jogador para um local adjacente.
     * @param destino Nome da saída ou direção.
     * @return Descrição do novo local ou mensagem de erro.
     */
    public String mover(String destino) {
        Local novo = localAtual.obterSaida(destino);
        if (novo != null) {
            localAtual = novo;
            return olhar();
        }
        return "Nao pode ir para la.";
    }

    /**
     * Descreve o ambiente onde o jogador se encontra.
     * @return Descrição completa do local.
     */
    public String olhar() {
        return localAtual.obterDescricaoCompleta();
    }

    /**
     * Realiza uma inspeção detalhada de uma entidade ou tenta apanhar um item.
     * Valida se o jogador possui a Lupa para ver detalhes escondidos.
     *
     * @param nome Nome da entidade a inspecionar.
     * @return O resultado da inspeção.
     */
    public String inspecionar(String nome) {
        // --- REGRA DA LUPA ---
        // Se o jogador NAO tem a Lupa no inventario...
        if (!inventario.temItem("Lupa")) {
            // ... e se o objeto que esta a tentar ver NAO e a propria Lupa...
            if (!nome.equalsIgnoreCase("Lupa")) {
                // ... entao bloqueia a acao.
                return "A vista desarmada nao consegue ver detalhes importantes.\nPrecisa de encontrar uma ferramenta de investigacao (Lupa) primeiro.";
            }
        }

        // Se passar a verificacao (tem Lupa OU esta a apanhar a Lupa), continua:
        iEntidade e = localAtual.obterEntidade(nome);
        if (e == null) return "Nao ve '" + nome + "' aqui.";

        // Se for item, apanha
        if (e instanceof ItemColecionavel) {
            localAtual.removerEntidade(nome);
            inventario.adicionarItem((ItemColecionavel) e);
            return "Inspecionaste e guardaste: " + e.obterNome();
        }

        // Se for NPC ou Objeto, devolve a descricao
        return e.inspecionar(this);
    }

    /**
     * Inicia uma conversa com um NPC.
     * @param nome Nome do NPC.
     * @return A fala do NPC.
     */
    public String falar(String nome) {
        iEntidade e = localAtual.obterEntidade(nome);
        if (e instanceof NPC) {
            return ((NPC) e).conversar(this);
        }
        return "Nao pode falar com isso.";
    }

    /**
     * Entrega um item do inventário a um NPC.
     * @param nomeItem Nome do item a dar.
     * @param nomeNPC Nome do recetor.
     * @return Resultado da interação.
     */
    public String darItem(String nomeItem, String nomeNPC) {
        if (!inventario.temItem(nomeItem)) {
            return "Nao tem '" + nomeItem + "' no inventario.";
        }

        iEntidade e = localAtual.obterEntidade(nomeNPC);
        if (e == null || !(e instanceof NPC)) {
            return "Nao ves '" + nomeNPC + "' aqui para dar o item.";
        }

        NPC npc = (NPC) e;
        String resultado = npc.receberItem(this, nomeItem);
        
        if (!resultado.contains("nao quer isso")) {
            inventario.removerItem(nomeItem);
        }
        
        return resultado;
    }

    /**
     * Ativa o efeito de um item utilizável.
     * @param nome Nome do item.
     * @return Resultado do uso.
     */
    public String usarItem(String nome) {
        itens.iItem item = inventario.obterItem(nome);
        if (item instanceof iUtilizavel) {
            return ((iUtilizavel) item).usar(this);
        }
        return "Nao tem esse item ou nao pode ser usado.";
    }
    
    /**
     * Consulta os detalhes de uma pista no caderno.
     * @param nome Nome da pista.
     * @return A informação da pista.
     */
    public String examinarPista(String nome) {
        Pista p = inventario.obterPista(nome);
        if (p != null) return p.obterInformacao();
        return "Pista nao encontrada.";
    }

    public Inventario obterInventario() { return inventario; }
    public Local obterLocalAtual() { return localAtual; }
}