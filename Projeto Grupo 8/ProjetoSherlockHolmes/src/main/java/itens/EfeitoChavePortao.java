package itens;
import nucleo.Jogador;

/**
 * Implementa a lógica de utilização da Chave do Portão.
 * Verifica se o jogador está no local correto e desbloqueia o acesso à Quinta Vigia.
 *
 * @author Grupo 8
 */
public class EfeitoChavePortao implements iEfeitoItem {
    
    /**
     * Tenta usar a chave.
     * Se o jogador estiver nos "Portoes da Quinta", a chave é consumida e o caminho abre-se.
     *
     * @param j O jogador.
     * @return Mensagem de sucesso ou de falha.
     */
    @Override
    public String executar(Jogador j) {
         if (j.obterLocalAtual().obterNome().toLowerCase().contains("portoes")) {
            j.obterInventario().removerItem("Chave do Portao");
            return "Inseres a chave pesada na fechadura enferrujada.\nCLACK.\nO portao geme e abre-se.\n>>> AGORA PODE IR PARA: 'Quinta Vigia'";
         }
         return "Esta chave nao abre nada aqui.";
    }
}