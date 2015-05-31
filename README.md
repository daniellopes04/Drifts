# Drifts

Projeto de 2015/1 de Computa¸c˜ao II - Java
Adriano Joaquim de Oliveira Cruz
15 de maio de 2015
1 Informa¸c˜oes Gerais
Neste trabalho, os alunos ter˜ao de desenvolver um jogo em Java para rodar em desktops
e/ou laptops. Alternativamente, ser´a aceito que o jogo seja desenvolvido em Java para
algum dispositivo m´ovel (celular, telem´ovel, tablet). Neste caso, nenhum suporte ser´a
fornecido. O jogo dever´a ser desenvolvido em grupos de at´e trˆes alunos.
Al´em de entregar, em meio eletrˆonico, o execut´avel e os fontes do programa, os grupos
dever˜ao elaborar e entregar um manual do usu´ario, contendo informa¸c˜oes sobre como
instalar e executar o jogo, bem como informa¸c˜oes sobre o jogo em si. A entrega do
manual ´e obrigat´oria e a nota n˜ao ser´a dada se este requisito nˆao for cumprido.
2 Avalia¸c˜ao
A entrega do trabalho ser´a no laborat´orio LEP1 em dias e hor´arios que ser˜ao marcados.
Este jogo vale at´e 1.0 ponto a mais na m´edia global do curso.
Cada grupo ter´a meia hora para fazer sua apresenta¸c˜ao. Nesta apresenta¸c˜ao, os grupos
dever˜ao entregar o material pedido bem como demonstrar o funcionamento do jogo. Al´em
disto, dever˜ao estar aptos a responder quest˜oes sobre o desenvolvimento do jogo.
O grupo que marcar uma data para `a apresenta¸c˜ao e n˜ao comparecer ter´a
descontado meio ponto de sua m´edia global. Lembro que basta que o grupo compare¸ca
apresentando pelo menos um percentagem pequena do trabalho para que n˜ao seja
feito este desconto. O importante ´e que o grupo mostre que houve interesse em fazer o
trabalho.
O jogo ser´a avaliado como um todo, ou seja, os requisitos n˜ao receber˜ao pontua¸c˜oes
individualmente. Dessa forma, a falta de um ou mais requisitos acarretar´a na perda de
pontos, que poder´a ser compensada (n˜ao totalmente, claro) atrav´es de outros componentes
bem desenvolvidos.
2
Componentes adicionais ser˜ao muito bem vistos, desde que implementados de maneira
racional. Lembre-se de usar o bom senso para n˜ao transformar criatividade em bagun¸ca.
3 O Jogo
O jogo ´e conhecido como Drifts e uma vers˜ao que pode ser jogada na Internet pode ser
encontrada em http://www.repeatwhiletrue.com/drifts/. O usu´ario comanda com
o mouse o movimento de uma bolha vermelha em uma ´area da tela. O objetivo do
jogo ´e colar `a bolha vermelha o maior n´umero de bolhas verdes poss´ıvel evitando as
bolhas p´urpuras. Tocar uma bolha azul transforma suas bolhas verdes em pontos e as faz
desaparecer. E necess´ario estar carregando pelo menos trˆes bolhas verdes p ´ ara receber
pontos. Mais bolhas verdes significa um aumento importante do n´umero de pontos. Caso
uma das bolhas pertencentes ao grupo de bolhas comandadas pelo mouse toque na bolha
p´urpura o jogo termina.
A bolha vermelha ´e comandada pelo movimento do mouse dentro da tela principal do
jogo. O restante das bolhas aparecem na parte superior da ´area do jogo e se deslocam
pela tela com velocidade e dire¸c˜ao constantes.
Somente ´e necess´ario verificar colis˜oes entre a(s) bolha(s) controlada(s) pelo mouse
e o restante das bolhas. As bolas que se movimentam sozinhas se comportam como se
estivessem se movendo em planos paralelos `a tela e n˜ao se tocam apenas ocultam as que
est˜ao nos planos de fundo.
4 Contagem de Pontos
E necess´ario agrupar trˆes bolhas verdes para que seja contad ´ o um ponto. Bolhas adicionais
conferem pontos extras.
A Tabela 4 mostra os pontos obtidos para algumas quantidades de bolhas no jogo
citado na se¸c˜ao 3.
5 Telas
Nesta se¸c˜ao iremos apresentar as telas que comp˜oem o jogo.
5.1 Tela Inicial
O jogo deve possuir uma tela inicial, que deve ser apresentada ao jogador antes da tela
com o jogo em si. A tela inicial deve pelo menos conter o nome do jogo, os nomes
3
Bolhas Pontos
3 1
4 5
5 11
6 17
7 25
8 33
9 41
10 51
Tabela 1: Bolhas versus pontos
dos integrantes do grupo, e permitir a navega¸c˜ao para outras telas. Estas outras telas
obrigat´orias s˜ao as seguintes:
1. uma com instru¸c˜oes sobre o jogo (navega¸c˜ao opcional);
2. uma para a configura¸c˜ao do jogo (navega¸c˜ao opcional);
3. uma tela com uma lista de recordistas. A quantidade de recordistas que aparece na
lista ´e escolha dos desenvolvedores (navegador opcional).
4. uma tela para identifica¸c˜ao do jogador. Esta tela deve obrigatoriamente aparecer
entre a tela inicial e a tela do jogo.
5. tela do jogo.
5.2 Tela de Instru¸c˜oes
Esta tela deve apresentar, de forma detalhada, instru¸c˜oes sobre o jogo.
5.3 Tela de Configura¸c˜ao
Nesta tela o jogador pode configurar op¸c˜oes do jogo. Pelo menos deve ser poss´ıvel con-
figurar se haver´a som ou n˜ao. Outras possibilidades ser˜ao apresentadas na se¸c˜ao de
Componentes Adicionais (Se¸c˜ao 6).
5.4 Tela do Jogo
A tela do principal do jogo deve conter pelo menos os seguintes itens:
• o nome do jogo em destaque;
4
• um espa¸co para contagem dos pontos do jogador;
• um espa¸co para mostrar o tempo restante do jogo (caso o jogo tenha a op¸c˜ao de
jogar por tempo);
• uma poss´ıvel indica¸c˜ao da fase em que est´a o jogo (caso o jogo tenha diversas fases).
5.5 Tela de Identifica¸c˜ao
Nesta tela o jogo deve pedir o nome do jogador para permitir o armazenamento de recordes.
5.6 Tela Final
A tela final deve permitir ao jogador poder jogar mais uma vez. Ela deve mostrar o
recorde do jogador e o resultado deste turno. Caso o resultado deste turno seja maior que
o recorde antigo o jogador deve ser parabenizado.
5.7 T´ermino do Jogo
O jogo termina em trˆes situa¸c˜oes:
• quando o agrupamento de bolhas toca na bolha p´urpura;
• o jogador apertou a tecla escape;
• o tempo de jogo acabou (se houver esta op¸c˜ao).
6 Componentes Adicionais
O grupo que seguir e implementar os requisitos de maneira correta receber´a uma nota
razo´avel. Os grupos est˜ao livres para adicionar outros componentes ao jogo para que
recebam notas melhores. Por´em, antes de fazer altera¸c˜oes radicais em rela¸c˜ao ao que foi
pedido, leia a se¸c˜ao 2.
Alguns poss´ıveis componentes adicionais:
• Pode-se fazer o jogo ser composto por diversas fases com graus crescentes de difi-
culdades.
• Pode-se implementar o jogo por tempo, ou seja o turno tem um tempo definido.
• Pode-se implementar o conceito de vidas, ou seja quando o as bolhas do jogador
explodem ele pode jogar mais um n´umero determinado de pontos.
