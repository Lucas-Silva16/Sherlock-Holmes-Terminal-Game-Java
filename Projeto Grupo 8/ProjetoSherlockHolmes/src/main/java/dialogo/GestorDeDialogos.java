package dialogo;

import java.util.Arrays;
import java.util.Collections;

/**
 * Classe utilitária responsável por centralizar e fornecer os diálogos de todos os NPCs do jogo.
 * Funciona como uma base de dados estática de textos, evitando que as strings fiquem espalhadas
 * por várias classes.
 *
 * @author Grupo 8
 */
public class GestorDeDialogos {

    /**
     * Carrega e devolve uma instância de iDialogo correspondente à chave fornecida.
     * Contém todo o guião do jogo.
     *
     * @param chave O identificador único do NPC ou objeto (ex: "vendedora_antiga", "tecnico_porto").
     * @return Um objeto DialogoLinear configurado com as falas correspondentes, ou um diálogo vazio se a chave não existir.
     */
    public static iDialogo carregarDialogo(String chave) {
        
        // --- OBJETOS GERAIS ---
        if (chave.equals("parede")) {
            return new DialogoLinear(Collections.singletonList("(E apenas uma parede suja. Talvez devesses inspeciona-la com atencao.)"));
        }

        // --- AMBIENTE / EXTRAS ---
        if (chave.equals("turista_bebado")) {
            return new DialogoLinear(Arrays.asList(
                "I LOVE MADEIRA SO MUCHHHHHHHHHHHHHHH LETS GO!!!!",
                "RONALDO!! SIUUUUUUUUUUUUUUUUUUUUUUUUU 67 67 67 67",
                "SDISADASHDHASJjhd"
            ));
        }
        if (chave.equals("turista_ingles")) {
            return new DialogoLinear(Arrays.asList(
                "OH MY GOD SHERLOCK HOLMES!!!!",
                "NO WAYYY CAN WE TAKE A PICTURE?",
                "Cheers... I love it!"
            ));
        }
        if (chave.equals("turista_perdido")) {
            return new DialogoLinear(Arrays.asList(
                "Excuse me... I am lost.",
                "Is the Teleferico working?",
                "This market smells like blood and fish."
            ));
        }

        // --- NIVEL 1: ZONA VELHA ---
        if (chave.equals("O Meias")) {
            return new DialogoLinear(Arrays.asList(
                "Chefe...Diga-me oque quer saber...vou tentar ajudar no que posso...",
                "O Bloom nao e droga de rua... e veneno da gente rica para agente pobre...",
                "Estou a bater o dente... o frio entra pela caveira dentro..."
            ));
        }
        if (chave.equals("Maike Tayson")) {
            return new DialogoLinear(Arrays.asList(
                "Se eu apanho esse sacana do meias eu mato ele e mato me!",
                "Vai mamar na quinta pata do cavalo!",
                "Nao aviso outra vez faco te num oito enquanto o diabo esfrega o olho!"
            ));
        }
        if (chave.equals("dono_tasca")) {
            return new DialogoLinear(Arrays.asList(
                "ENTAO CHEFE! OQUE VAI SER HOJE!!",
                "Sim, anda ai um gajos estranhos a fazer negocio... Isto na mamadeira anda muito estranho...",
                "Bebe isto. Vai te aquecer e vais precisar para o que vais ver a seguir."
            ));
        }

        // --- NIVEL 2: MERCADO ---
        if (chave.equals("vendedora_antiga")) {
            return new DialogoLinear(Arrays.asList(
                "A fruta hoje esta cara, meu senhor. A inflacao no Funchal nao perdoa.", 
                "Quer informacoes? Eu nao sou informadora, sou vendedora...",
                "Mas... se se tornar um 'bom cliente', talvez me lembre de algo...Vai lhe custar 50 euros um saquinho com fruta filhinho"
            ));
        }
        if (chave.equals("estafeta")) {
            return new DialogoLinear(Arrays.asList(
                "Estou feito... eles vao matar-me se nao entrego a carga...",
                "A chave... caiu no meio do lixo ou das tripas de peixe...",
                "Sem a carrinha, sou um homem morto."
            ));
        }

        // --- NIVEL 3: CANICAL (ENTRADA & INTERIOR) ---
        if (chave.equals("tecnico_porto")) {
            return new DialogoLinear(Arrays.asList(
                "Alto la! Zona restrita. A cancela esta fechada.",
                "Nao passa ninguem sem autorizacao... ou sem me dar algo para passar o tempo.",
                "Isto aqui e uma seca... nem um jornal tenho para ler."
            ));
        }
        if (chave.equals("estivador")) {
            return new DialogoLinear(Arrays.asList(
                "Sai da frente! Tenho de descarregar o Lobo Marinho antes das 7.",
                "Estas a procura de sarilhos? Fala com o Quimico, ele e que anda a tremer.",
                "As minhas costas estao a matar-me..."
            ));
        }
        if (chave.equals("supervisor")) {
            return new DialogoLinear(Arrays.asList(
                "Quem e voce? Sindicato? Aqui no Grupo Sousa trabalhamos a serio!",
                "O Armazem 4? Isso e alugado a privados. Nao temos nada a ver com isso.",
                "Circule, ou chamo a seguranca!"
            ));
        }
        if (chave.equals("manobrador")) {
            return new DialogoLinear(Arrays.asList(
                "La de cima da grua ve-se tudo, amigo.",
                "Vi caixas a entrar no Armazem 4 a meio da noite. Carros de luxo, vidros fumados.",
                "Se eu fosse a ti, tinha cuidado. Acidentes acontecem muito aqui."
            ));
        }
        if (chave.equals("quimico")) {
            return new DialogoLinear(Arrays.asList(
                "Nao... nao me toques! A minha cabeca vai explodir!",
                "Preciso... preciso dos meus calmantes...",
                "Deixei-os cair... Sem os comprimidos... nao consigo pensar... nao consigo falar!"
            ));
        }

        // --- NIVEL 4: PORTOES & SEGURANÇA ---
        if (chave.equals("seguranca_mau")) {
            return new DialogoLinear(Collections.singletonList("Home vai te embora! Tas aqui a fazer oque? Propriedade privada ANDOR DAQUI!"));
        }
        if (chave.equals("seguranca_corrupto")) {
            return new DialogoLinear(Arrays.asList(
                "O Presidente nao recebe ninguem. Muito menos a esta hora.",
                "O portao esta trancado. E a chave custa caro amigo.",
                "Digamos que... 20 euros pagam a minha 'distracao' momentanea."
            ));
        }

        // --- NIVEL 5: PUZZLE DO CORREDOR (PISTAS) ---
        if (chave.equals("quadro_ferry")) {
            return new DialogoLinear(Collections.singletonList(
                "Uma pintura a oleo do navio 'Lobo Marinho'. Na boia lateral, ves o numero pintado [2], na proa do barco ves outros numeros: [6] e [7]."
            ));
        }
        if (chave.equals("busto_ajj")) {
            return new DialogoLinear(Collections.singletonList(
                "O busto sorridente. A placa diz '1978', mas alguem riscou os numeros com uma faca, deixando apenas o [8]."
            ));
        }
        if (chave.equals("contentor_mini")) {
            return new DialogoLinear(Collections.singletonList(
                "Abres a miniatura do contentor azul. Dentro, uma etiqueta de carga diz: Lote [5]."
            ));
        }
        
        // --- NOVO: O BILHETE COM A FORMULA MATEMATICA ---
        if (chave.equals("bilhete_math")) {
            return new DialogoLinear(Collections.singletonList(
                "É um bilhete amarrotado deixado por um guarda:\n" +
                "'A nova senha do Gabinete é: A SOMA dos numeros do Quadro, seguida da MULTIPLICACAO do Busto pelo Contentor.'"
            ));
        }
        // --- FINAL: O CONFRONTO NO GABINETE ---
        if (chave.equals("alberto_final")) {
            return new DialogoLinear(Arrays.asList(
                "Oque e isto! Um cubano a mando de Lisboa? Ainda bem que chegaste! Usa as algemas neste homem imediatamente!",
                "Eu posso ter as minhas festas, mas ele... ele controla o que entra e sai nesta ilha!",
                "A droga nao vem nos meus bolsos, vem nos contentores do Grupo Sousa!",
                "Eu sou apenas um politico! Ele e que e o Barao da droga! O Dono Disto Tudo!"
            ));
        }
        
        if (chave.equals("luis_miguel")) {
            return new DialogoLinear(Arrays.asList(
                "(Voz calma e monotona, sem olhar para ti) Por favor... tenha dignidade, Alberto.",
                "Sr. Agente, o Presidente esta visivelmente alcoolizado e descontrolado.",
                "Eu sou apenas um gestor de logistica. Garanto que a ilha tem comida e cimento.",
                "Prenda-o. Ele e a instabilidade. Eu sou o progresso."
            ));
        }

        return new DialogoLinear(Collections.singletonList("..."));
    }
}