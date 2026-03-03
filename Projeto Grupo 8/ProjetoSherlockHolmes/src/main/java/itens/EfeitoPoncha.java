package itens;
import nucleo.Jogador;

/**
 * Implementa a lógica de consumo da Poncha.
 * O item é removido do inventário (consumido) e o jogador recebe feedback narrativo.
 *
 * @author Grupo 8
 */
public class EfeitoPoncha implements iEfeitoItem {
    
    /**
     * Executa o consumo da bebida.
     *
     * @param j O jogador.
     * @return Descrição da sensação de beber a poncha.
     */
    @Override
    public String executar(Jogador j) {
        j.obterInventario().removerItem("Poncha");
        return "Bebeste o liquido que hoje em dia vale mais que ouro e que queima as goelas.\nSentes-te quente, mas perdeste a moeda de troca para o sem-abrigo.";
    }
}