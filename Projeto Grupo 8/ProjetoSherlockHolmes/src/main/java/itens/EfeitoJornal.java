package itens;
import nucleo.Jogador;

/**
 * Implementa a lógica de leitura do Jornal.
 * Ao ler, o jogador ganha uma nova pista sobre os roubos no Caniçal.
 *
 * @author Grupo 8
 */
public class EfeitoJornal implements iEfeitoItem {
    
    /**
     * Executa a leitura do jornal.
     * Adiciona a pista ao inventário se o jogador ainda não a tiver.
     *
     * @param j O jogador.
     * @return O conteúdo da notícia.
     */
    @Override
    public String executar(Jogador j) {
        if (!j.obterInventario().temPista("Noticia de Jornal")) {
            j.obterInventario().adicionarPista(new Pista("Artigo do Jornal", "Trafico de drogas no Canical abafado pela policia."));
        }
        return "OCORRENCIAS MAMADEIRA:\n'Policia rejeita denuncias de trafico de droga nas docas do canical!!'\n[!] Nova dica.";
    }
}