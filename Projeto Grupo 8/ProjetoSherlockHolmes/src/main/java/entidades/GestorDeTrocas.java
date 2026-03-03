package entidades;

import dialogo.iDialogo;
import itens.ItemColecionavel;
import itens.Pista;
import nucleo.Jogador;
import nucleo.Local;

/**
 * Classe responsável por encapsular toda a lógica complexa de interações com NPCs,
 * incluindo lojas (compra/venda), missões (quests) e spawn de itens no mundo.
 * Segue o princípio da Responsabilidade Única, retirando esta lógica da classe NPC.
 *
 * @author Grupo 8
 */
public class GestorDeTrocas {
    // Variáveis de Loja
    private ItemColecionavel itemParaOferecer;
    private boolean jaOfereceuItem = false;
    private int precoInicial = 0;
    private int precoReposicao = 0;

    // Variáveis de Quest/Troca
    private String itemDesejado;
    private String mensagemPedido;
    private String respostaAoReceber;
    private Pista pistaSecreta;
    private boolean jaRecebeuItemDesejado = false;
    private int recompensaMonetaria = 0;

    // Variáveis de Spawn
    private Local localParaSpawn;
    private ItemColecionavel itemQuest;
    private boolean jaSpawnou = false;

    // --- MÉTODOS DE CONFIGURAÇÃO (Setters) ---
    
    /**
     * Configura o gestor para funcionar como uma loja.
     *
     * @param item      O item que o NPC vai vender.
     * @param precoIni  O preço da primeira vez que o item é oferecido.
     * @param precoRep  O preço para comprar o item novamente se for perdido.
     */
    public void definirItemVenda(ItemColecionavel item, int precoIni, int precoRep) {
        this.itemParaOferecer = item;
        this.precoInicial = precoIni;
        this.precoReposicao = precoRep;
    }

    /**
     * Configura uma missão (Quest).
     *
     * @param itemDesejado O nome do item que o NPC quer.
     * @param msgPedido    O que o NPC diz quando pede o item.
     * @param msgSucesso   O que o NPC diz quando recebe o item.
     * @param pista        A pista dada como recompensa (pode ser null).
     * @param recompensa   Dinheiro dado como recompensa.
     */
    public void definirQuest(String itemDesejado, String msgPedido, String msgSucesso, Pista pista, int recompensa) {
        this.itemDesejado = itemDesejado;
        this.mensagemPedido = msgPedido;
        this.respostaAoReceber = msgSucesso;
        this.pistaSecreta = pista;
        this.recompensaMonetaria = recompensa;
    }

    /**
     * Define que um item deve aparecer num local específico após uma interação (Spawn).
     *
     * @param local O local onde o item vai aparecer.
     * @param item  O item a ser criado.
     */
    public void definirSpawn(Local local, ItemColecionavel item) {
        this.localParaSpawn = local;
        this.itemQuest = item;
    }

    // --- LÓGICA PRINCIPAL (Movida do NPC) ---

    /**
     * Processa a interação contínua com o NPC, verificando estados de loja e quests.
     *
     * @param j       O jogador.
     * @param npc     O NPC dono deste gestor.
     * @param dialogo O diálogo atual.
     * @return Uma String com a reação do NPC, ou vazia se nada especial acontecer.
     */
    public String processarInteracao(Jogador j, NPC npc, iDialogo dialogo) {
        // Lógica de Reposição (Loja)
        if (jaOfereceuItem && itemParaOferecer != null) {
            if (!j.obterInventario().temItem(itemParaOferecer.obterNome()) && precoReposicao > 0) {
                if (j.obterInventario().obterDinheiro() >= precoReposicao) {
                    j.obterInventario().gastarDinheiro(precoReposicao);
                    j.obterInventario().adicionarItem(itemParaOferecer);
                    return npc.obterNome() + ": \"Perdeste outra vez? Toma la. Sao " + precoReposicao + " euros.\"";
                } else {
                    return npc.obterNome() + ": \"Queres mais? Custa " + precoReposicao + " euros, e tu estas liso!\"";
                }
            }
        }

        // Lógica de Spawn de Itens (Quest)
        if (itemDesejado != null && !jaRecebeuItemDesejado) {
            if (dialogo.terminou()) {
                if (localParaSpawn != null && itemQuest != null && !jaSpawnou) {
                    jaSpawnou = true;
                    localParaSpawn.adicionarEntidade(itemQuest);
                    System.out.println("\n[!] (DICA: O " + itemQuest.obterNome() + " apareceu no local: " + localParaSpawn.obterNome() + ")");
                }
                String msg = (mensagemPedido != null) ? mensagemPedido : "Preciso de algo...";
                return npc.obterNome() + ": \"" + msg + "\"";
            }
        }
        
        return ""; // Retorna vazio se nada especial aconteceu
    }

    /**
     * Tenta realizar a venda inicial de um item quando o jogador termina o diálogo pela primeira vez.
     *
     * @param j   O jogador.
     * @param npc O NPC vendedor.
     */
    public boolean tentarVendaInicial(Jogador j, NPC npc) { 
            if (itemParaOferecer != null && !jaOfereceuItem) {
                if (precoInicial > 0) {
                    if (j.obterInventario().gastarDinheiro(precoInicial)) { 
                        j.obterInventario().adicionarItem(itemParaOferecer);
                        jaOfereceuItem = true;
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    j.obterInventario().adicionarItem(itemParaOferecer);
                    jaOfereceuItem = true;
                    return true;
                }
            }
            return false;
        }

    /**
     * Processa a lógica quando o jogador dá um item ao NPC (para completar uma quest).
     *
     * @param j        O jogador.
     * @param npc      O NPC receptor.
     * @param nomeItem O nome do item dado.
     * @return A resposta do NPC.
     */
    public String processarRecebimento(Jogador j, NPC npc, String nomeItem) {
        if (itemDesejado == null) return npc.obterNome() + " nao quer isso.";

        if (nomeItem.equalsIgnoreCase(itemDesejado)) {
            if (!jaRecebeuItemDesejado) {
                jaRecebeuItemDesejado = true;
                
                if (pistaSecreta != null) j.obterInventario().adicionarPista(pistaSecreta);
                if (recompensaMonetaria > 0) j.obterInventario().adicionarDinheiro(recompensaMonetaria);
                
                return npc.obterNome() + ": \"" + respostaAoReceber + "\"";
            } else {
                return npc.obterNome() + " ja tem o que queria.";
            }
        }
        return npc.obterNome() + ": \"Nao quero isso. Tens " + itemDesejado + "?\"";
    }
}