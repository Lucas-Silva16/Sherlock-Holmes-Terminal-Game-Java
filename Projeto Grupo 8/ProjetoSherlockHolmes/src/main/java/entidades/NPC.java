package entidades;

import dialogo.iDialogo;
import itens.Pista;
import nucleo.Jogador;

/**
 * Representa uma Personagem Não Jogável (Non-Playable Character) no mundo do jogo.
 * <p>
 * Os NPCs são entidades interativas que possuem nome, descrição e um sistema de diálogo.
 * Podem conter pistas ocultas (reveladas através da inspeção com itens específicos)
 * e realizar transações complexas ou missões (quests) delegando lógica num {@link GestorDeTrocas}.
 * </p>
 *
 * @author Grupo 8
 * @version 1.1
 */
public class NPC implements iEntidade {
    private String nome;
    private String descricao;
    
    /**
     * Estratégia de diálogo (ex: Linear) utilizada por este NPC.
     */
    private iDialogo dialogo;
    
    // Inspeção Visual (Mantemos aqui pois faz parte da descrição do NPC)
    private boolean jaFoiInspecionado = false;
    private Pista pistaInspecao;
    private Pista pistaConversa;
    private boolean jaDeuPistaConversa = false;
    
    /**
     * O gestor responsável pela lógica de comércio e missões.
     * Utiliza-se Composição: o NPC "tem um" gestor.
     */
    private GestorDeTrocas gestor; 

    /**
     * Constrói um novo NPC com os atributos básicos.
     *
     * @param nome      O nome identificador da personagem (ex: "Vendedora Antiga").
     * @param descricao A descrição visual apresentada ao jogador.
     * @param dialogo   A instância do sistema de diálogo contendo as falas.
     */
    public NPC(String nome, String descricao, iDialogo dialogo) {
        this.nome = nome;
        this.descricao = descricao;
        this.dialogo = dialogo;
    }

    /**
     * Associa um gestor de trocas a este NPC.
     * Permite que o NPC tenha comportamentos de loja (vender itens) ou quests (pedir itens).
     *
     * @param gestor A instância do gestor configurada.
     */
    public void definirGestor(GestorDeTrocas gestor) {
        this.gestor = gestor;
    }

    /**
     * Define uma pista que será revelada apenas quando o NPC for inspecionado com a Lupa.
     *
     * @param p A pista visual oculta.
     */
    public void definirPistaInspecao(Pista p) { 
        this.pistaInspecao = p; 
    }

    /**
     * Define uma pista que será entregue ao jogador após concluir o diálogo (e eventuais pagamentos).
     *
     * @param p A pista narrativa.
     */
    public void definirPistaConversa(Pista p) {
        this.pistaConversa = p; 
    }

    @Override
    public String obterNome() { return nome; }

    @Override
    public String obterDescricao() { return descricao; }

    /**
     * Realiza uma inspeção detalhada ao NPC.
     * <p>
     * Se o jogador possuir o item "Lupa" no inventário, este método revela a {@code pistaInspecao}
     * caso ela exista. Também contém lógica específica para objetos de cenário que se comportam
     * como NPCs (ex: Quadros, Bustos) para devolverem falas de descrição.
     * </p>
     *
     * @param j O jogador que realiza a inspeção.
     * @return O texto resultante da inspeção (descrição base ou detalhe revelado).
     */
    @Override
    public String inspecionar(Jogador j) {
        // Lógica visual simples
        if (nome.contains("Miniatura") || nome.contains("Busto") || nome.contains("Quadro")) {
             return dialogo.obterProximaFala();
        }

        System.out.println(descricao);
        
        if (pistaInspecao != null) {
            if (j.obterInventario().temItem("Lupa")) {
                if (!jaFoiInspecionado) {
                    jaFoiInspecionado = true;
                    j.obterInventario().adicionarPista(pistaInspecao);
                }
                return "[!] (Lupa) Detalhe: " + pistaInspecao.obterDescricao();
            } else {
                return "(Parece haver algo estranho aqui, mas a olho nu nao consegues ver. Precisas de uma Lupa.)";
            }
        }
        return "";
    }

    /**
     * Gere o fluxo de conversação com o jogador.
     * <p>
     * Este método coordena o sistema de diálogo linear com o {@link GestorDeTrocas}.
     * Se o NPC tiver algo para vender, a transação é tentada no final do diálogo.
     * A pista de conversa só é entregue se a transação for bem-sucedida (ou se não houver custos).
     * </p>
     *
     * @param j O jogador que está a conversar.
     * @return A próxima fala do NPC ou a reação a uma transação.
     */
    public String conversar(Jogador j) {
        if (gestor != null) {
            String resultadoGestor = gestor.processarInteracao(j, this, dialogo);
            if (!resultadoGestor.isEmpty()) return resultadoGestor;
        }

        String falaAtual = dialogo.obterProximaFala();
        
        if (dialogo.terminou() && !jaDeuPistaConversa) {
             if (gestor != null) {
                 // Tentar a venda e guardar o resultado
                 boolean sucessoCompra = gestor.tentarVendaInicial(j, this);
                 
                 // SE FALHOU A COMPRA: Retornamos apenas a fala sem dar a pista
                 if (!sucessoCompra) {
                     return nome + ": \"" + falaAtual + "\""; 
                 }
             }

             // SE CHEGOU AQUI: A compra foi um sucesso (ou não há gestor)
             if (pistaConversa != null) {
                 j.obterInventario().adicionarPista(pistaConversa);
             }
             jaDeuPistaConversa = true;
        }
        
        return nome + ": \"" + falaAtual + "\"";
    }

    /**
     * Processa a receção de um item entregue pelo jogador.
     * A lógica de aceitação ou rejeição é inteiramente delegada no {@link GestorDeTrocas}.
     *
     * @param j        O jogador que entrega o item.
     * @param nomeItem O nome do item oferecido.
     * @return A resposta do NPC à oferta.
     */
    public String receberItem(Jogador j, String nomeItem) {
        if (gestor == null) return nome + " nao aceita itens.";
        return gestor.processarRecebimento(j, this, nomeItem);
    }
}