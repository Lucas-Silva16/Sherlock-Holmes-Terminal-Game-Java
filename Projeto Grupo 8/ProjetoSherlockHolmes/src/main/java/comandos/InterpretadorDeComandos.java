package comandos;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import Excecoes.ComandoInvalidoException; 

/**
 * Responsável por processar a entrada de texto do utilizador e transformá-la
 * em objetos de comando executáveis.
 * Utiliza o padrão Command e um Mapa (HashMap) para associar palavras-chave a classes.
 *
 * @author Grupo 8
 * @version 1.0
 */
public class InterpretadorDeComandos {
    
    /**
     * Mapa que associa Strings (ex: "ir") a instâncias de comandos (ex: ComandoIr).
     */
    private Map<String, iComando> comandos;

    /**
     * Construtor que inicializa o mapa e regista todos os comandos disponíveis no jogo.
     */
    public InterpretadorDeComandos() {
        comandos = new HashMap<>();
        comandos.put("ir", new ComandoIr());
        comandos.put("olhar", new ComandoOlhar());
        comandos.put("falar", new ComandoFalar());
        comandos.put("inspecionar", new ComandoInspecionar());
        comandos.put("inventario", new ComandoInventario());
        comandos.put("pistas", new ComandoPistas());
        comandos.put("examinar", new ComandoExaminar());
        comandos.put("usar", new ComandoUsar());
        comandos.put("dar", new ComandoDar()); 
        comandos.put("ajuda", new ComandoAjuda());
        comandos.put("deduzir", new ComandoDeduzir());
        comandos.put("sair", new ComandoSair());
    }

    /**
     * Analisa a string de entrada, extrai a palavra-chave e devolve o comando correspondente.
     *
     * @param input A linha de texto digitada pelo jogador.
     * @return A instância do comando correspondente à palavra-chave.
     * @throws ComandoInvalidoException Se a primeira palavra não corresponder a nenhum comando registado.
     */
    public iComando analisar(String input) throws ComandoInvalidoException {
        String[] partes = input.trim().split(" ");
        String chave = partes[0].toLowerCase();
        
        if (comandos.containsKey(chave)) {
            return comandos.get(chave);
        }
        
        // LANÇAR A EXCEÇÃO PERSONALIZADA
        throw new ComandoInvalidoException(chave);
    }
    
    /**
     * Utilitário para separar os argumentos da palavra-chave do comando.
     * Remove a primeira palavra (o comando) e devolve o resto como um array.
     *
     * @param input A linha de texto completa.
     * @return Array contendo apenas os argumentos (ex: se input for "ir zona velha", devolve ["zona velha"]).
     */
    public String[] obterArgumentos(String input) {
        String[] partes = input.trim().split(" ");
        if (partes.length > 1) return Arrays.copyOfRange(partes, 1, partes.length);
        return new String[0];
    }
}