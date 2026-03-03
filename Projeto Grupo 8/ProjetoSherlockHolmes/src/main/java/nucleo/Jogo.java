package nucleo;

import comandos.InterpretadorDeComandos;
import comandos.iComando;
import itens.ItemUtilizavel;
import Excecoes.JogoException; 
import java.util.Scanner;

/**
 * Classe principal que gere o ciclo de vida do jogo (Game Loop).
 * Responsável por inicializar os componentes, processar a entrada do jogador
 * e gerir a progressão entre níveis.
 *
 * @author Grupo 8
 */
public class Jogo {
    private Jogador jogador;
    private InterpretadorDeComandos interpretador;
    private boolean jogoAtivo;
    private int nivelAtual = 1;
    private Mundo mundo;

    /**
     * Inicializa o jogo, construindo o mundo e o jogador.
     */
    public Jogo() {
        interpretador = new InterpretadorDeComandos();
        jogoAtivo = true;
        
        // Construtor do Mundo (SOLID)
        ConstrutorMundo construtor = new ConstrutorMundo();
        this.mundo = construtor.construir();
        
        jogador = new Jogador(mundo.getLocalInicial());
        
        // Item Algemas (Item especial de fim de jogo)
        ItemUtilizavel algemas = new ItemUtilizavel("Algemas", "Metal frio e resistente. Essenciais para a justica.", 
            (j) -> "Ainda nao e a altura. Tens de ter o culpado a tua frente e a confissao dele.");
        jogador.obterInventario().adicionarItem(algemas);
    }

    /**
     * Inicia o ciclo principal do jogo, aguardando e processando comandos até o jogo terminar.
     */
    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n================================================");
        System.out.println("      SOMBRAS DO FUNCHAL: A AMEACA BLOOM");
        System.out.println("================================================");
        System.out.println("A noite caiu sobre o Funchal. A chuva parou, mas a calcada");
        System.out.println("ainda brilha com o reflexo das luzes amarelas e fracas.");
        System.out.println("");
        System.out.println("Es Sherlock Holmes. Nao vieste pelo clima, mas pelo dinheiro.");
        System.out.println("Uma nova droga sintetica, 'Bloom', esta a transformar");
        System.out.println("as ruas da ilha num pesadelo cinzento.");
        System.out.println("");
        System.out.println("A policia nao ve nada. Ou finge que nao ve.");
        System.out.println("Estas na Zona Velha. O cheiro a tabaco e segredos paira no ar.");
        System.out.println("Confia na tua Lupa. Desconfia de todos.");
        System.out.println("================================================");
        
        System.out.println("\n--- COMANDOS DISPONIVEIS ---");
        System.out.println(" > ir [nome do local]    : Mover-se (Ex: 'ir Mercado dos Lavradores')");
        System.out.println(" > olhar                 : Ver o que esta a tua volta");
        System.out.println(" > falar [nome]          : Conversar com um personagem");
        System.out.println(" > dar [item] [nome]     : Entregar um item a alguem");
        System.out.println(" > usar [item]           : Usar um item do inventario(Ex:Podes beber ou ler)");
        System.out.println(" > inspecionar [nome]    : Observar detalhes e as pessoas / Apanhar itens");
        System.out.println(" > digitar [codigo]      : Inserir codigos em paineis/cofres"); 
        System.out.println(" > inventario            : Ver itens e dinheiro");
        System.out.println(" > pistas                : Ver o teu caderno de notas");
        System.out.println(" > deduzir               : Tentar resolver o misterio do nivel(Depois de teres as pistas todas)");
        System.out.println(" > ajuda                 : Listar todos os comandos novamente");
        System.out.println(" > sair                  : Fechar o jogo");
        System.out.println("----------------------------------------------------------------------------------");
        
        System.out.println(jogador.olhar());

        while (jogoAtivo) {
            System.out.print("\n[Nivel " + nivelAtual + "] > ");
            String input = scanner.nextLine();
            System.out.println(processarComando(input));
        }
        
        System.out.println("\nO caso foi arquivado. Obrigado por jogar.");
        scanner.close();
    }

    /**
     * Processa a string introduzida pelo jogador, executando comandos ou mecanismos.
     *
     * @param input O texto digitado.
     * @return A resposta do jogo.
     */
    public String processarComando(String input) {
        if (input.trim().isEmpty()) return "";
        String comandoLimpo = input.toLowerCase().trim();
        Local localAtual = jogador.obterLocalAtual();

        // 0. FAILSAFE DO PORTO (Lógica específica de desbloqueio de mapa)
        if (localAtual.obterNome().equals("Entrada do Porto")) {
            if (jogador.obterInventario().temPista("Acesso ao Porto")) {
                if (localAtual.obterSaida("porto") == null) {
                    Local portoInterior = mundo.getLocal("porto industrial");
                    localAtual.adicionarSaida("Porto Industrial", portoInterior);
                    localAtual.adicionarSaida("porto industrial", portoInterior);
                    localAtual.adicionarSaida("porto", portoInterior);
                    return "[SISTEMA] A cancela reconheceu a tua autorizacao e abriu-se automaticamente.\n" +
                           ">>> CAMINHO ABERTO: Podes escrever 'ir porto'";
                }
            }
        }

        // --- DIGITAR (Para mecanismos como cofres e portas) ---
        if (comandoLimpo.startsWith("digitar")) {
            if (localAtual.ativarMecanismos(input)) {
                return ""; 
            }
            
            // Lógica Específica do Cofre (Armazém 4) - Exemplo de Override local
            if (localAtual.obterNome().equals("Armazem 4")) {
                if (input.toUpperCase().contains("A67H")) {
                    ItemUtilizavel manifesto = new ItemUtilizavel("Manifesto de Carga", "Documentos que ligam o trafico diretamente a Quinta Vigia.", j-> "Documentos incriminatorios.");
                    if (!jogador.obterInventario().temItem("Manifesto de Carga")) {
                        jogador.obterInventario().adicionarItem(manifesto);
                        return "[SUCESSO] Bip. Bip. Clack! O cofre abre-se.\nLa dentro encontras papeis importantes.\n[+] Item obtido: Manifesto de Carga";
                    } else {
                        return "O cofre ja esta aberto e vazio.";
                    }
                } else {
                    return "[ERRO] O cofre apita. Codigo incorreto. (Dica: Procura grafitis nas paredes dos locais anteriores)";
                }
            }
            return "Nao ha nada para digitar aqui.";
        }

        try {
            iComando cmd = interpretador.analisar(input);
            if (cmd instanceof comandos.ComandoSair) jogoAtivo = false;
            
            String resultado = cmd.executar(this, interpretador.obterArgumentos(input));
            
            // --- EVENTOS DE DESBLOQUEIO DE MAPA (Triggers) ---
            if (resultado.contains("Levantei a cancela") || resultado.contains("Acesso ao Porto")) {
                Local entrada = mundo.getLocal("entrada porto");
                Local portoInterior = mundo.getLocal("porto industrial");
                entrada.adicionarSaida("Porto Industrial", portoInterior);
                entrada.adicionarSaida("porto industrial", portoInterior);
                entrada.adicionarSaida("porto", portoInterior);
                return resultado + "\n>>> CAMINHO ABERTO: Porto Industrial";
            }
            if (resultado.contains("O portao geme e abre-se")) {
                Local portoes = mundo.getLocal("portoes da quinta");
                Local corredor = mundo.getLocal("corredor"); 
                portoes.adicionarSaida("Corredor da Quinta", corredor); 
                portoes.adicionarSaida("corredor", corredor);        
                return resultado + "\n>>> CAMINHO ABERTO: Corredor da Quinta";
            }
            
            return resultado;
            
        } catch (JogoException e) { 
            return "[ERRO] " + e.getMessage();
        } catch (Exception e) {
            return "[ERRO CRITICO] " + e.getMessage();
        }
    }

    public Jogador obterJogador() { return jogador; }

    /**
     * Verifica se o jogador cumpre as condições (pistas/itens) para avançar para o próximo nível/capítulo.
     * @return Mensagem de sucesso ou dica do que falta.
     */
    public String resolverPuzzleNivel() {
        // Nivel 1 -> 2
        if (nivelAtual == 1) {
            if (jogador.obterInventario().temPista("Nome da droga: Bloom") && 
                jogador.obterInventario().temPista("Origem desconhecida") && 
                jogador.obterInventario().temPista("Flores Secas")) {
                nivelAtual = 2;
                Local zonaVelha = mundo.getLocal("zona velha");
                Local mercado = mundo.getLocal("mercado");
                zonaVelha.adicionarSaida("Mercado dos Lavradores", mercado);
                zonaVelha.adicionarSaida("mercado dos lavradores", mercado);
                return "\n[!] DEDUCAO CORRETA\nAs pistas e os murmurinhos levam-te ao Mercado dos Lavradores.\n>>> NOVO LOCAL: Mercado dos Lavradores";
            }
            return "Ainda te faltam pistas. O Sem-abrigo parece deslocado neste cenario...\nAs palavras dele nao chegam. Tenta examinar a sua aparencia com mais atencao (e a ferramenta certa).";
        }

        // Nivel 2 -> 3
        if (nivelAtual == 2) {
            if (jogador.obterInventario().temPista("Fuga a Alfandega") && jogador.obterInventario().temPista("Caixas sem etiqueta")) {
                nivelAtual = 3;
                Local mercado = mundo.getLocal("mercado");
                Local entradaPorto = mundo.getLocal("entrada porto");
                mercado.adicionarSaida("Entrada do Porto", entradaPorto); 
                mercado.adicionarSaida("entrada do porto", entradaPorto); 
                return "\n[!] DEDUCAO CORRETA\nO Mercado e apenas um entreposto. A podidao vem do mar.\nTens de ir investigar o Porto Industrial do Canical.\n>>> NOVO LOCAL: Entrada do Porto";
            }
            return "Ainda nao sabes como a droga entra. Ajuda o Estafeta e 'compra' informacoes a quem as tem.";
        }
        
        // Nivel 3 -> 4
        if (nivelAtual == 3) {
             if (jogador.obterInventario().temPista("Codigo Porta Armazem") && !jogador.obterInventario().temItem("Manifesto de Carga")) {
                 return "\n[!] DEDUCAO PARCIAL\nConseguiste o codigo (1978). Agora vai ao 'Painel de Controlo' e usa o comando 'digitar 1978' para desbloquear a porta.";
             }
             if (jogador.obterInventario().temItem("Manifesto de Carga")) {
                nivelAtual = 4;
                Local canical = mundo.getLocal("porto industrial");
                Local armazem = mundo.getLocal("armazem"); 
                Local portoes = mundo.getLocal("portoes da quinta");
                canical.adicionarSaida("Portoes da Quinta", portoes);
                canical.adicionarSaida("portoes da quinta", portoes);
                armazem.adicionarSaida("Portoes da Quinta", portoes);
                armazem.adicionarSaida("portoes da quinta", portoes);
                return "\n[!] DEDUCAO CORRETA\nO Manifesto prova tudo: O laboratorio e financiado pela Quinta Vigia.\nA corrupcao chega ao topo.\n>>> NOVO LOCAL: Portoes da Quinta";
            }
            return "Conseguiste entrar no Armazem, mas tens de abrir o Cofre para obter provas!";
        }
        
        // FINAL
        if (nivelAtual == 4) {
             if (jogador.obterLocalAtual().obterNome().equals("Gabinete Presidencial")) {
                 if (jogador.obterInventario().temPista("Confissao de AJJ") && jogador.obterInventario().temPista("Confissao de LMS")) {
                     jogoAtivo = false;
                     return "\n*******************************************************\n" +
                            "              VITORIA TOTAL - JUSTICA FEITA            \n" +
                            "*******************************************************\n" +
                            "Com todas as pecas do puzzle juntas, tu sacas das ALGEMAS.\n" +
                            "Num movimento rapido, prendes o Alberto Joao Jardim e o Dr. Luis Miguel.\n" +
                            "Alberto Joao Jardim: 'Isto e um golpe de estado SEU CUBANO MALDITO!!!'\n" +
                            "LMS: 'O Grupo Sousa nao vai gostar disto...Tas tramado Holmes!'\n\n" +
                            "O Bloom acabou. A ilha esta a salvo.\n" +
                            ">>> PARABENS! GANHASTE O JOGO. <<<";
                 } else {
                     return "Tens as provas fisicas, mas precisas das confissoes orais!\n" +
                            "Fala com o Alberto Joao Jardim e com o Luis Miguel Sousa para obteres as confissoes antes de deduzir.";
                 }
             }
             return "Entra no Gabinete Presidencial para confrontar os culpados.";
        }
        return "Nada a deduzir agora.";
    }
}