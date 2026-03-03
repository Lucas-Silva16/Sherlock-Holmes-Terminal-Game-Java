package comandos;
import nucleo.Jogo;
import java.util.Arrays;

/**
 * Implementa a lógica de entregar um item do inventário do jogador a um NPC.
 * Este comando possui uma lógica complexa para distinguir onde termina o nome do item
 * e onde começa o nome do NPC na frase introduzida.
 *
 * @author Grupo 8
 */
public class ComandoDar implements iComando {

    /**
     * Executa a tentativa de troca.
     * O método itera sobre os argumentos para tentar encontrar uma combinação válida
     * de "Nome do Item" vs "Nome do NPC".
     *
     * @param j    A instância atual do jogo.
     * @param args As palavras digitadas após "dar" (ex: "chave", "da", "carrinha", "estafeta").
     * @return Mensagem de sucesso da troca ou mensagem de erro se o item/NPC não for encontrado.
     */
    public String executar(Jogo j, String[] args) {
        if (args.length < 2) {
            return "Dar o que a quem? (Ex: dar poncha sem-abrigo)";
        }

        // Tenta encontrar onde acaba o nome do item e comeca o nome do NPC
        for (int i = 1; i < args.length; i++) {
            // Tenta combinar as primeiras 'i' palavras para ver se e um item valido
            String nomeItemPotencial = String.join(" ", Arrays.copyOfRange(args, 0, i));
            
            // Verifica se o jogador tem este item
            if (j.obterJogador().obterInventario().temItem(nomeItemPotencial)) {
                
                // Se tem, o resto da frase e o nome do NPC
                String nomeNPCPotencial = String.join(" ", Arrays.copyOfRange(args, i, args.length));
                
                // Executa a troca delegando ao Jogador
                return j.obterJogador().darItem(nomeItemPotencial, nomeNPCPotencial);
            }
        }

        return "Nao possui nenhum item com esse nome (verifique se escreveu o nome completo corretamente).";
    }
}