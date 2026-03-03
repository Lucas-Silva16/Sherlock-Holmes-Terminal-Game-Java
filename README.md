🕵️‍♂️ Sombras do Funchal: A Ameaça Bloom Sombras do Funchal é um jogo de aventura e mistério baseado em texto (CLI) desenvolvido em Java. O jogador assume o papel do lendário detetive Sherlock Holmes, enviado à ilha da Madeira para investigar a propagação de uma nova droga sintética chamada "Bloom".

O projeto foca-se na aplicação rigorosa de princípios de Programação Orientada a Objetos (POO) e boas práticas de engenharia de software.

📖 A História A noite caiu pesada sobre o Funchal. Entre as ruelas da Zona Velha e o luxo da Quinta Vigia, uma rede de corrupção silencia a cidade.

O Objetivo: Coletar pistas, interagir com personagens suspeitos, gerir recursos (dinheiro e itens) e deduzir a verdade para prender os culpados.

Os Níveis: O jogo progride através de 4 níveis de dedução, desde as ruas sujas até ao Gabinete Presidencial.

🛠️ Tecnologias e Arquitetura O projeto foi desenhado seguindo a filosofia SOLID para garantir que o código seja fácil de manter e expandir.

Padrões de Projeto Utilizados: Command Pattern: Utilizado no pacote comandos para encapsular cada ação do jogador (ir, falar, pegar) em classes independentes, facilitando a adição de novos comandos sem alterar o motor principal.

Strategy (Lambdas): Implementado nos ItemUtilizavel para definir comportamentos dinâmicos no momento da criação do item.

State/Gestores: Uso de um GestorDeTrocas para separar a lógica de negócio e transações da classe NPC.

Hierarquia de Exceções: O jogo utiliza Checked Exceptions personalizadas (JogoException) para lidar com erros de lógica (ex: tentar entrar num local trancado), garantindo que o programa não termine abruptamente e forneça feedback útil ao utilizador.
