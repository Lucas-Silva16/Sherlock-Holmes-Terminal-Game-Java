package nucleo;

import dialogo.GestorDeDialogos;
import entidades.GestorDeTrocas;
import entidades.NPC;
import itens.*;
import entidades.*;
import mecanicas.MecanismoCodigo;

/**
 * Classe responsável pela criação e configuração de todo o universo do jogo.
 * Instancia os locais, itens, NPCs, diálogos e define as conexões entre eles.
 * Funciona como uma "Fábrica" (Factory Pattern) do mundo.
 *
 * @author Grupo 8
 */
public class ConstrutorMundo {

    /**
     * Constrói o jogo, interligando locais e populando-os com entidades.
     *
     * @return O objeto Mundo completamente configurado e pronto a jogar.
     */
    public Mundo construir() {
        Mundo mundo = new Mundo();

        // --- 1. ZONA VELHA ---
        Local zonaVelha = new Local("Zona Velha", 
            "A noite caiu pesada sobre o Funchal. A calcada brilha, humida e escorregadia,\n" +
            "sob a luz amarela e doente dos candeeiros de rua. O ar e espesso, carregado\n" +
            "de fumo de tabaco e o cheiro doce e enjoativo da poncha.\n" + 
            "Entre as sombras das ruelas estreitas, ves uma parede grafitada estranha ao fundo..." 
        );
        
        // ITENS COM EFEITOS (Usando as classes concretas do pacote itens)
        ItemColecionavel lupa = new ItemUtilizavel("Lupa", "Vidro riscado, mas funcional.", new EfeitoLupa());
        ItemColecionavel jornal = new ItemUtilizavel("Jornal", "Jornal encharcado com noticias de crimes.", new EfeitoJornal());
        ItemColecionavel poncha = new ItemUtilizavel("Poncha", "Ouro liquido da Madeira.", new EfeitoPoncha());

        zonaVelha.adicionarEntidade(lupa);
        zonaVelha.adicionarEntidade(jornal);
        
        // --- NPC: Sem-abrigo (QUEST) ---
        NPC semAbrigo = new NPC("O Meias", "Meias o maior politico dos agarrados da RAM", GestorDeDialogos.carregarDialogo("O Meias"));
        semAbrigo.definirPistaInspecao(new Pista("Origem desconhecida", "Roupas de marca imundas... carimbo do Mercado."));
        
        // Configuração do Gestor de Trocas para o Sem-abrigo
        GestorDeTrocas gestorSemAbrigo = new GestorDeTrocas();
        gestorSemAbrigo.definirQuest(
            "Poncha",                                             
            "Estou a congelar... preciso do calor da cana...", 
            "Obrigado... O Bloom e feito para a elite...",     
            new Pista("Nome da droga: Bloom", "Droga de elite chamada Bloom."), 
            0 
        );
        semAbrigo.definirGestor(gestorSemAbrigo); 
        
        // --- NPC: Tasqueiro (LOJA) ---
        NPC tasqueiro = new NPC("Dono da Tasca", "Limpa copos com um pano encardido.", GestorDeDialogos.carregarDialogo("dono_tasca"));
        tasqueiro.definirPistaConversa(new Pista("Flores Secas", "Pacotes que parecem flores secas."));
        
        GestorDeTrocas gestorTasqueiro = new GestorDeTrocas();
        gestorTasqueiro.definirItemVenda(poncha, 0, 5); 
        tasqueiro.definirGestor(gestorTasqueiro); 
        
        NPC paredeZV = new NPC("Parede Grafitada", "Uma parede cheia de riscos e desenhos.", GestorDeDialogos.carregarDialogo("parede"));
        paredeZV.definirPistaInspecao(new Pista("Codigo Parte 1", "No meio dos riscos ves uma letra grande: 'A'"));

        zonaVelha.adicionarEntidade(new NPC("Turista Bebado", "Olhar vitreo...", GestorDeDialogos.carregarDialogo("turista_bebado")));
        zonaVelha.adicionarEntidade(new NPC("Turista Ingles", "Veste tweed...", GestorDeDialogos.carregarDialogo("turista_ingles")));
        zonaVelha.adicionarEntidade(semAbrigo);
        zonaVelha.adicionarEntidade(tasqueiro);
        zonaVelha.adicionarEntidade(new NPC("Maike Tayson", "Conhecido como o mais conhecido dos agarrados AKA O mais conhecido", GestorDeDialogos.carregarDialogo("Maike Tayson")));
        zonaVelha.adicionarEntidade(paredeZV);

        // --- 2. MERCADO ---
        Local mercado = new Local("Mercado dos Lavradores", "Um ataque aos sentidos. O cheiro ferroso a sangue de peixe mistura-se\ncom o aroma doce e enjoativo das frutas exoticas a apodrecer no calor.\nVendedores gritam como gaivotas, disputando a atencao de turistas alheados.\nNo meio do caos, atras de uma banca vazia, ves uma parede grafitada.");
        mercado.adicionarEntidade(new ItemColecionavel("Chave da Carrinha", "Chave com porta-chaves do CR7, caida no chao."));

        // --- NPC: Vendedora (LOJA) ---
        NPC vendedora = new NPC("Vendedora Antiga", "Olhar desconfiado...", GestorDeDialogos.carregarDialogo("vendedora_antiga"));
        ItemColecionavel sacoFruta = new ItemColecionavel("Saco de Fruta", "Fruta fresca, mas custou uma fortuna.");
        
        GestorDeTrocas gestorVendedora = new GestorDeTrocas();
        gestorVendedora.definirItemVenda(sacoFruta, 50, 0); 
        vendedora.definirGestor(gestorVendedora);
        
        vendedora.definirPistaConversa(new Pista("Fuga a Alfandega", 
            "Papel diz: 'A carga ilegal vem do outro lado da ilha...Da terra dos pescadores'"));

        // --- NPC: Estafeta (QUEST + DINHEIRO) ---
        NPC estafeta = new NPC("Estafeta", "Suado, palido...", GestorDeDialogos.carregarDialogo("estafeta"));
        
        GestorDeTrocas gestorEstafeta = new GestorDeTrocas();
        gestorEstafeta.definirQuest(
            "Chave da Carrinha", 
            "Perdi a chave! O patrao vai matar-me!", 
            "Ufa! Salvaste-me! Toma la 60 euros...", 
            new Pista("Caixas sem etiqueta", "Destino: Canical."), 
            60 
        );
        estafeta.definirGestor(gestorEstafeta);
        
        NPC paredeMerc = new NPC("Parede Grafitada", "Parede suja...", GestorDeDialogos.carregarDialogo("parede"));
        paredeMerc.definirPistaInspecao(new Pista("Codigo Parte 2", "Alguem riscou dois numeros aqui: '67'"));
        
        mercado.adicionarEntidade(new NPC("Turista Perdido", "Segura um mapa...", GestorDeDialogos.carregarDialogo("turista_perdido")));
        mercado.adicionarEntidade(vendedora);
        mercado.adicionarEntidade(estafeta);
        mercado.adicionarEntidade(paredeMerc);

        // --- 3. PORTO ---
        Local entradaPorto = new Local("Entrada do Porto", "Chegas a entrada do Porto Industrial."
                + " A vedacao e alta e tem arame farpado.\nUma cancela automatica bloqueia a passagem para o interior."
                + "\nA cabine do tecnico aduaneiro tem a luz acesa.");
        
        // --- NPC: Tecnico (QUEST DE ACESSO) ---
        NPC tecnico = new NPC("Tecnico Portuario", "Fuma um cigarro nervoso...", GestorDeDialogos.carregarDialogo("tecnico_porto"));
        
        GestorDeTrocas gestorTecnico = new GestorDeTrocas();
        gestorTecnico.definirQuest(
            "Jornal", 
            "Que seca de turno... nem um jornal para ler.", 
            "Ahhh, obrigado! Sendo assim... Podes entrar.", 
            new Pista("Acesso ao Porto", "Cancela aberta."), 
            0
        );
        tecnico.definirGestor(gestorTecnico);
        entradaPorto.adicionarEntidade(tecnico);

        Local portoInterior = new Local("Porto Industrial", "Contentores gigantes erguem-se como muralhas de ferro contra o ceu negro.\nO cheiro a oleo queimado e maresia e sufocante.\nVes o Armazem 4 ao fundo, mas a porta parece trancada.\nHa varios trabalhadores do Grupo Sousa a carregar caixas e um homem estranho escondido.\nAo lado da porta do Armazem, existe um PAINEL DE CONTROLO e uma parede grafitada.");
        Local painel = new Local("Painel de Controlo", "Estas em frente ao painel eletronico do Armazem 4.\nO ecra pisca vermelho e pede um codigo numerico.");
        Local armazem = new Local("Armazem 4", "O ar la dentro e abafado e cheira a quimicos.\nUma lampada fraca pisca no teto, revelando pilhas de caixas com o simbolo da Quinta Vigia.\nNum canto escuro, ves um cofre robusto com um teclado para 4 caracteres.\nA unica saida e a porta nas tuas costas.");

        // MECANISMO
        painel.adicionarMecanismo(new MecanismoCodigo("1978", 
            "[SUCESSO] O painel pisca verde. Ouves um clique hidraulico alto.\nA porta do Armazem 4 foi destrancada.\n>>> CAMINHO ABERTO: Armazem 4", 
            "[ERRO] O painel pisca vermelho. Codigo incorreto.", 
            armazem, "Armazem 4"));

        // --- NPC: Quimico (QUEST + SPAWN) ---
        NPC quimico = new NPC("Quimico", "Olhos esbugalhados, pele cinzenta, escondido atras de contentores.", GestorDeDialogos.carregarDialogo("quimico"));
        ItemColecionavel calmantes = new ItemColecionavel("Calmantes", "Uma caixa de comprimidos 'Diazepam' pisada no chao.");
        
        GestorDeTrocas gestorQuimico = new GestorDeTrocas();
        gestorQuimico.definirSpawn(mercado, calmantes); 
        gestorQuimico.definirQuest(
            "Calmantes", 
            "A minha cabeca... preciso dos meus comprimidos... perdi-os no mercado...", 
            "Ahhh... obrigado... O codigo do Painel e o ano em que o Alberto Joao subiu ao poder... 1978.", 
            new Pista("Codigo Porta Armazem", "Codigo do Painel: 1978."),
            0
        );
        quimico.definirGestor(gestorQuimico);
        
        NPC paredeCan = new NPC("Parede Grafitada", "Parede de metal enferrujado com tinta spray.", GestorDeDialogos.carregarDialogo("parede"));
        paredeCan.definirPistaInspecao(new Pista("Codigo Parte 3", "A ultima letra esta aqui: 'H'"));

        portoInterior.adicionarEntidade(new NPC("Estivador Cansado", "Suado, a carregar caixas de bananas.", GestorDeDialogos.carregarDialogo("estivador")));
        portoInterior.adicionarEntidade(new NPC("Supervisor do Sousa", "Veste um colete limpo e grita com os outros.", GestorDeDialogos.carregarDialogo("supervisor")));
        portoInterior.adicionarEntidade(new NPC("Manobrador de Grua", "Esta numa pausa, a fumar.", GestorDeDialogos.carregarDialogo("manobrador")));
        portoInterior.adicionarEntidade(quimico);
        portoInterior.adicionarEntidade(paredeCan);

        // --- 4. QUINTA VIGIA ---
        Local portoes = new Local("Portoes da Quinta", "Estas diante do poder. Portoes de ferro macico, coroados com o brasao da Madeira,\nseparam a rua escura do luxo iluminado da Quinta Vigia.\nA entrada e barrada por dois segurancas:\nUm tem a mao na arma e cara de poucos amigos. O outro esfrega os dedos e sorri de forma cinica.");
        
        ItemUtilizavel chavePortao = new ItemUtilizavel("Chave do Portao", "Uma chave dourada.", new EfeitoChavePortao());

        // --- NPC: Seguranca Corrupto (LOJA) ---
        NPC segurancaCorrupto = new NPC("Seguranca Corrupto", "Sorriso cinico e macabro", GestorDeDialogos.carregarDialogo("seguranca_corrupto"));
        
        GestorDeTrocas gestorSeguranca = new GestorDeTrocas();
        gestorSeguranca.definirItemVenda(chavePortao, 20, 20); 
        segurancaCorrupto.definirGestor(gestorSeguranca);
        
        portoes.adicionarEntidade(new NPC("Seguranca Mau", "Mao no coldre e cara de poucos amigos", GestorDeDialogos.carregarDialogo("seguranca_mau")));
        portoes.adicionarEntidade(segurancaCorrupto);

        Local corredor = new Local("Corredor da Quinta", "Entraste num corredor luxuoso.\nA porta do Gabinete Presidencial esta fechada e tem um teclado numerico.\nVes um Quadro, um Busto, uma Miniatura e um Bilhete no chao.");
        Local gabinete = new Local("Gabinete Presidencial", "O escritorio e opulento. Alberto Joao Jardim e o Dr. Luis Miguel Sousa estao a discutir.\nTens as provas e as algemas, so te falta as confissoes.\n");

        // MECANISMO
        corredor.adicionarMecanismo(new MecanismoCodigo("1540", 
            "[SUCESSO] Bip-Bip A luz fica verde. A porta abre-se.\n>>> CAMINHO ABERTO: Gabinete Presidencial", 
            "[ERRO] O teclado emite um som agudo. Codigo errado. (Procura o bilhete com a formula matematica)", 
            gabinete, "Gabinete Presidencial"));

        corredor.adicionarEntidade(new NPC("Quadro Lobo Marinho", "Pintura a oleo", GestorDeDialogos.carregarDialogo("quadro_ferry")));
        corredor.adicionarEntidade(new NPC("Busto AJJ", "Estatua de bronze", GestorDeDialogos.carregarDialogo("busto_ajj")));
        corredor.adicionarEntidade(new NPC("Miniatura Contentor", "Contentor azul.", GestorDeDialogos.carregarDialogo("contentor_mini")));
        corredor.adicionarEntidade(new NPC("Bilhete", "Codigo porta: Soma dos numeros do ferry e multiplicacao do busto pelo contentor", GestorDeDialogos.carregarDialogo("bilhete_math")));

        NPC jardim = new NPC("Alberto Joao Jardim", "Esta furioso.", GestorDeDialogos.carregarDialogo("alberto_final"));
        jardim.definirPistaConversa(new Pista("Confissao de AJJ", "Admitiu que o esquema vem de cima."));
        
        NPC luisMiguel = new NPC("Dr. Luis Miguel de Sousa", "Esta calmo.", GestorDeDialogos.carregarDialogo("luis_miguel"));
        luisMiguel.definirPistaConversa(new Pista("Confissao de LMS", "Admitiu a gestao logistica do Bloom."));
        
        gabinete.adicionarEntidade(jardim);
        gabinete.adicionarEntidade(luisMiguel);

        // --- LIGAR O MUNDO ---
        mercado.adicionarSaida("Zona Velha", zonaVelha);
        entradaPorto.adicionarSaida("Mercado dos Lavradores", mercado);
        
        portoInterior.adicionarSaida("Entrada do Porto", entradaPorto);
        portoInterior.adicionarSaida("Painel de Controlo", painel);
        painel.adicionarSaida("Voltar", portoInterior);
        armazem.adicionarSaida("Porto Industrial", portoInterior);
        armazem.adicionarSaida("porto industrial", portoInterior);
        
        corredor.adicionarSaida("Portoes da Quinta", portoes); 

        // REGISTAR
        mundo.adicionarLocal("zona velha", zonaVelha);
        mundo.adicionarLocal("mercado", mercado);
        mundo.adicionarLocal("entrada porto", entradaPorto);
        mundo.adicionarLocal("porto industrial", portoInterior);
        mundo.adicionarLocal("painel", painel);
        mundo.adicionarLocal("armazem", armazem);
        mundo.adicionarLocal("portoes da quinta", portoes);
        mundo.adicionarLocal("corredor", corredor);
        mundo.adicionarLocal("gabinete", gabinete);
        
        mundo.setLocalInicial(zonaVelha);
        return mundo;
    }
}