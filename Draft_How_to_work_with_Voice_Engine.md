## Criar um novo exercício de voz ##

Para criar um novo exercício no ivela-voice, é necessário primeiramente fazer o download do julius no site VoxForge.

Feito o download, instale o Julius. Em seguida, deve-se criar uma gramática e um vocabulário para o novo exercício. Para isso é necessário criar dois novos arquivos na pasta grammar ques está dentro da pasta raiz do julius.

  1. O arquivo .grammar define o conjunto de regras que governará o reconhecimento. O arquivo de gramática do julius usa "Categorias de Palavras"- que é um nome para uma lista de palavras para serem reconhecidas(que estão definidas no arquivo .voca)
  1. O arquivo .voca define as "Palavras Candidatas" e informações de suas pronúncias em cada categoria de palavras
O arquivo .grammar

As regras que gorvernam as palavras permitidas são definidas no arquivo .grammar usando uma modificação do formato BNF . A gramatica espedificada no Julian usa um conjunto de regras de derivação, escritas como:

```
Símbolo: [expressão com simbolos]
```

onde:

  * Símbolo é um não terminal; e
  * [expressão com simbolos] é uma expressão que consiste de uma sequência de simbolos que podem ser terminal e/ou não terminal

Um terminal é um símbolo que representa um constante valor. Ele nunca aparece a esquerda do dois pontos. No Julian um terminal represeta uma Categorias de Palavras.

Um não terminal é uma simbolo que pode ser expressado em termo de outros simbolos. Ele pode ser substituido It can be replaced as a result of substitution rules.

Por exemplo, veja a seguinte regra de derivação:
```
S : NS_B LOOKUP NS_E

LOOKUP: CONNECT NAME
```
Neste exemplo, "S" é o simbolo inicial da sentença. NS\_B e NS\_E corresponde ao silêncio que ocorre antes e depois da sentença pronunciada. "S", "NS\_B" e "NS\_E" são requiridas em todas as gramáticas do Julian.

"NS\_B", "NS\_E", "CONNECT", e "NAME" são terminais, e representam Categorias de Palavras que devem ser definidas no arquivo ".voca" No arquivo ".voca","CONNECT" corresponde a duas palavras: "PHONE" e "CALL". "NAME" corresponde a duas palavras:"STEVE" e "YOUNG" . "LOOKUP" é um não terminal e não está definido no arquivo .voca. Ele tem sua definição completa no aquivo .grammar, onde ele é substituido pela expressão "CONNECT NAME". Todos os não terminais devem ser completamente definidos no arquivo .grammar. Eles serão substituídos por terminais que estão definidos no arquivo .voca como Categoria de palavras. Com Julian, apenas uma Regra de Substituição por linha é permitida, com os ":" como separador. Caracteres ASCII alfa-numericos e underscore são permitidos para nomes de Simbolos e esses são case sensitive.
Arquivo .voca

O arquivo ".voca" contém Definição de palavras para cada Categoria de palavras definida no arquivo .grammar.

Cada categoria de palavra deve ser definida com % na frente da definição. Each Word Category must be defined with "%" preceding it. Em cada categoria de palavras, a definição das palavras é feita uma a cada linha. A primeira coluna é a string que será escrita na tela pelo reconhecedor, e o resto é a pronuncia da palavra. Espaços e tabs atuam como separador de campos.

Formato:
```
%[Word Category]

[Word Definition] [pronunciation ...]

...
```
Por exemplo a Categoria de Palavra "NS\_B", "NS\_E", "CONNECT", e "NAME" foram referenciadas no arquivo .grammar acima e estão definidas no ".voca", como segue:
```
% NS_B

< s > sil

% NS_E

< / s > sil

% CONNECT

PHONE f ow n

CALL k ao l

% NAME

STEVE s t iy v

YOUNG y ah ng
```
No exemplo acima, as categorias de palavras, NS\_B e NS\_E tem uma palavra , 'sil' está definida no modelo acustico .Sendo a primeira corresponde ao silêcio inicial e a ultima ao silêncio no final.

"CONNECT" é dividido em duas palavras "PHONE" e "CALL" com informações da pronúncia, que são fonemas que estão inclusos,também, no Modelo Acustico. "NAME" é dividido em duas palavras: "STEVE" e "YOUNG" e seus fonemas

As palavras aceitas pelo modelo acústico e os seus fonemas podem ser encontrados Aqui
Compilando a nova gramática

Os arquivos .grammar e .voca precisam ser compilados para gerar os arquivos".dfa" e ".dict" para que o Julian possa usa-los. Isto é feito usando o compilador de gramática do Julian "mkdfa.pl". Os arquivos .grammar e .voca precisam ter o mesmo prefixo no seu nome, e este prefixo deve ser especificado para o script mkdfa.pl. Compile seus arquivos(sample.grammar and sample.voca) como o exemplo:
```
$ mkdfa.pl sample

sample.grammar has 3 rules

sample.voca has 6 categories and 18 words

---

Now parsing grammar file

Now modifying grammar to minimize states[-1]

Now parsing vocabulary file

Now making nondeterministic finite automaton[6/6]

Now making deterministic finite automaton[6/6]

Now making triplet list[6/6]

---

generated: sample.dfa sample.term sample.dict
```
Integrando com o applet
Criando o instalador

Para que o Applet possa funcionar corretamente, o mesmo necessita que as gramáticas e o executável do julian estejam instalados em seus devidos diretórios dependendo do sistema operacional. No Windows, o diretório está em C:\.julius, em uma pasta oculta. No Linux, o diretório se encontra em seu\_home/.julius. Em ambos os sitemas operacionais, a estrutura de arquivos dentro da pasta .julius procede da seguinte maneira (de forma geral):
```
->.julius

-> config.properties

-> julius

-> nome_conf.jconf

-> julian (exe no windows)

-> acoustic_model_files_build726/

->modelo_acústico

-> grammar

->arquivos_grammar/
```
  * **config.properties** é o arquivo que contém a versão mais atual do instalador localizado em um servidor remoto. O applet verifica essa versão com o seu parâmetro de entrada juliusVersion . Caso sejam diferentes, o applet automaticamente irá baixar a versão localizada no servidor remoto e instalá-la nos sistema de arquivos local do cliente.

  * **nome\_conf.jconf** é o arquivo de configuração. Neste arquivo devem estar explicitados os caminhos para o modelo acústico, e para a gramática a ser carregada pelo applet. Tanto no windows quanto no linux, este arquivo deve possuir particularidades. Nas próximas seções iremos apresentar um exemplo simples de montagem de instalador. Não entraremos em detalhes agora.

  * **julian** é o programa excutável para o reconhecimento de voz. Roda no cliente.

  * **acoustic\_model\_files\_build726** é o modelo acústico para a língua inglesa. Atualmente no build 726.

  * **grammar** é a pasta onde ficam os arquivos relacionados a uma gramática.

## Exemplo de instalador (linux) ##

Vamos demonstrar um pequeno exemplo de como criar um instalador para o julius no linux. Inicialmente, o desenvolvedor deve estar de posse do executável do julian e do modelo acústico. Ambos podem ser encontrados no site do Voxforge. O desenvolvedor deve também explicitar no arquivo config.properties a versão do instalador. Para isto, basta adcionar a linha "version=1.1.7", por exemplo. Este mesmo número deverá servir de parâmetro de entrada para o Applet em seu HTML, como veremos mais adiante.

No diretório "grammar", vamos colocar nossos arquivos referentes a uma gramática exemplo. Como explicado anteriormente, se tivermos um arquivo sample.voca e uma arquivo **sample.grammar**, sua compilação irá gerar outros novos arquivos, **sample.dfa e sample.dict**. Colocaremos estes arquivos diretamente em "grammar". O próximo passo é criar o arquivo de configuração, o qual referencia estas compilações.

O arquivo de configuração deverá ficar no mesmo da pasta "grammar", como mostrado no exemplo mais geral acima. Criamos então o arquivo **sample.conf**. Duas linhas neste arquivo são de extrema importância. As linhas
```
-dfa grammar/word_stat/sample.dfa

-v grammar/word_stat/sample.dict
```
indicam ao programa julian onde estão localizadas fisicamente em disco os arquivos relativos a compilação dos arquivos .voca e .grammar. O resto do arquivo cabe as configurações default e não são cobertas por este tutorial. Você pode ver um exemplo deste arquivo aqui. A versão windows deve apenas ter cuidado no momento de escrever o caminho dos arquivos. O resto do tutorial é valido tambem para este sistema operacional.

Nossos arquivos então terão a seguinte estrutura de diretórios de acordo com o exemplo:
```
-> config.properties
-> julius
-> sample.jconf
-> julian
-> acoustic_model_files_build726/
-> grammar
-> sample.dfa
-> sample.dict
-> sample.grammar
-> sample.term
-> sample.voca
```
O desenvolvedor deverá agora compactar a pasta julius e o arquivo config.properties em um único arquivo chamada julius.zip. De posse do arquivo compactado, o desenvolvedor deverá torná-lo disponível ao applet em algum servidor. Se considerarmos o servidor Lighttp (linux) como exemplo, podemos criar em seu diretório de aplicações (/var/www/) a pasta public\_content/linux e nela colocar o nosso arquivo .zip. Portanto teríamos: /var/www/public\_content/linux/julius.zip.
Passando parâmetros ao Applet

Os parâmetros a serem passadas, na cláusula parâmetro, são:

  1. **question** Onde o value="Some title". Título simples.
  1. **exe** Onde o value="My name is Bob.#I am from Canada.#" são frases separadas por # e que são reconhecidas pela gramática.
  1. **conf** Onde o value="sample.jconf" é o arquivo de configuração que serve para a gramática que reconhece as frases acima.
  1. **audio** Onde o Value="bob.ogg#canada.ogg". São arquivos e audio, não obrigatórios referentes a cada frase acima. Devem também ser intaladas e disponíveis em um servidor http.
  1. **chances** Onde o value poderia ser = "5#3#", dando 5 chances de repetição para a frase 1 e 3 chances de repetição para a frase 2
  1. **audioHost** Onde o value poderia ser = "192.168.0.1:8080/public\_sound/". Este valor será concatenada com os valores de audio, dando o caminho relativo aos arquivos de audio
  1. **installerHost** Onde o value poderia ser = "192.168.0.1:8080/public\_installer". Neste caminho estariam as pastas windows e linux. Dentro destas pastas o arquivo julius.zip explicitado na seção anterior.
Rodando o Applet

Uma vez criando o instalador e definido os parâmetros, basta criar o html que irá suportar o applet. Os arquivos jar necessários são:

ivela\_voice.jar Jar principal com o programa de reconhecimento. Classe principal: br.ufc.ivela.voice.BlackBoardApplet.class
```
jogg-0.0.7.jar Relativo aos soms ogg.

jorbis-0.0.15.jar Relativo aos soms ogg.

tritonus_share.jar Relativo aos soms ogg.

vorbisspi1.0.3.jar Relativo aos soms ogg.
```