package nucleo;

import itens.Pista;
import itens.iItem;
import java.util.ArrayList;
import java.util.List;

/**
 * Gere os bens e conhecimentos do jogador.
 * Mantém listas separadas para itens físicos (que podem ser trocados) e pistas (conhecimento).
 * Também gere a carteira (dinheiro) do jogador.
 *
 * @author Grupo 8
 */
public class Inventario {
    private List<iItem> itens;
    private List<Pista> pistas;
    private int dinheiro; 

    /**
     * Inicializa um inventário vazio com um saldo inicial de 20 euros.
     */
    public Inventario() {
        this.itens = new ArrayList<>();
        this.pistas = new ArrayList<>();
        this.dinheiro = 20; 
    }

    /**
     * Devolve o saldo atual.
     * @return Valor em euros.
     */
    public int obterDinheiro() { return dinheiro; }

    /**
     * Tenta realizar uma transação financeira.
     *
     * @param valor O custo a pagar.
     * @return true se tiver saldo suficiente e o pagamento for efetuado.
     */
    public boolean gastarDinheiro(int valor) {
        if (dinheiro >= valor) {
            dinheiro -= valor;
            System.out.println("[-] Pagou " + valor + " euros. (Restam: " + dinheiro + ")");
            return true;
        }
        System.out.println("[!] Nao tem dinheiro suficiente!");
        return false;
    }
    
    /**
     * Adiciona dinheiro à carteira do jogador.
     * @param valor Quantidade a receber.
     */
    public void adicionarDinheiro(int valor) {
        dinheiro += valor;
        System.out.println("[+] Recebeu " + valor + " euros.");
    }

    /**
     * Guarda um item físico na mochila.
     * @param item O item a guardar.
     */
    public void adicionarItem(iItem item) {
        itens.add(item);
        System.out.println("[+] Item adicionado ao inventario: " + item.obterNome());
    }

    /**
     * Remove um item do inventário (consumido ou entregue).
     * @param nome Nome do item a remover.
     * @return true se o item existia e foi removido.
     */
    public boolean removerItem(String nome) {
        iItem item = obterItem(nome);
        if (item != null) {
            itens.remove(item);
            System.out.println("[-] Item removido do inventario: " + nome);
            return true;
        }
        return false;
    }

    /**
     * Regista uma nova pista no caderno de notas, evitando duplicados.
     * @param pista A pista descoberta.
     */
    public void adicionarPista(Pista pista) {
        boolean existe = pistas.stream().anyMatch(p -> p.obterNome().equalsIgnoreCase(pista.obterNome()));
        if (!existe) {
            pistas.add(pista);
            System.out.println("[!] Nova pista adicionada: " + pista.obterNome());
        }
    }

    public iItem obterItem(String nome) {
        return itens.stream().filter(i -> i.obterNome().equalsIgnoreCase(nome)).findFirst().orElse(null);
    }
    
    public Pista obterPista(String nome) {
        return pistas.stream().filter(p -> p.obterNome().equalsIgnoreCase(nome)).findFirst().orElse(null);
    }
    
    public boolean temItem(String nome) {
        return itens.stream().anyMatch(i -> i.obterNome().equalsIgnoreCase(nome));
    }

    public boolean temPista(String nome) {
        return pistas.stream().anyMatch(p -> p.obterNome().equalsIgnoreCase(nome));
    }

    /**
     * Gera uma lista textual dos itens físicos e do dinheiro atual.
     * @return String formatada do inventário.
     */
    public String listarItens() {
        StringBuilder sb = new StringBuilder("=== INVENTARIO ===\n");
        sb.append("Carteira: ").append(dinheiro).append(" euros\n"); // Mostra o dinheiro
        
        if (itens.isEmpty()) sb.append("(Mochila vazia)\n");
        else for (iItem i : itens) sb.append("- ").append(i.obterNome()).append("\n");
        
        return sb.toString();
    }

    /**
     * Gera uma lista textual das pistas descobertas.
     * @return String formatada do caderno de pistas.
     */
    public String listarPistas() {
        StringBuilder sb = new StringBuilder("=== CADERNO DE PISTAS ===\n");
        
        if (pistas.isEmpty()) {
            return "(O teu caderno de notas está vazio.)";
        }

        for (Pista p : pistas) {
            // Imprime o Título da Pista
            sb.append("- [").append(p.obterNome()).append("]\n");
            
            // Imprime a Informação/Detalhe
            sb.append("    > ").append(p.obterInformacao()).append("\n");
        }
        
        return sb.toString();
    }
}