# DesafioEngDados

QUESTÔES:

### 1-) Qual o objetivo do comando cache em Spark?

      O objetivo do comando cache doo spark, é carregar os dados em memória, para que fiquem disponíveis para futuras operações
      tornando-a mais eficiente, pois os dados ja estão carregados em memória.


### 2-) O mesmo código implementado em Spark é normalmente mais rápido que a implementação equivalente emMapReduce. Por quê?

      Sim, O mapReduce trabalha os dados no disco, isso torna as operações leitura/escrita custosa, já o spark pode ser até  100x mais rápido,
      pois ele trabalha os dados em mémoria em estruturas distribuidas(RDD), com isso o spark tem vantagens em analises que exigem 
      processamento rapido e processamento em tempo real.

### 3-) Qual é a função do SparkContext?

      O SparkContext tem como função conectar/inicializar recursos necessários para aplicação se conectar ao spark, e assim 
      disponiblizando diversas operaçoes para manipulação dos dados em memória. 

### 4-) Explique com suas palavras o que é Resilient Distributed Datasets (RDD)?

      Um RDD é uma estrutura de dados em memória tolerante a falhas,
      que lhe permite fazer operaçoes em grandes volumes de dados, disponiblizando mecanismos (algoritmos,machine learning)
      para manibulação desses dados  em  clusters sparks. 

### 5-)GroupByKey é menos eficiente que reduceByKey em grandes dataset. Por quê?

      Apesar de reproduzirem o mesmo resultado, o GroupByKey é menos eficiente, pois para agrupar primeiro ele mapeia todas 
      as chaves em todas as partições e depois sumariza as informaçes, comsumindo muita memória e tranferencia de rede, ja o reduceByKey, faz  o mapeamento eagrupamento em cada partição e depois sumariza.  

### 6-) Explique o que o código Scala abaixo faz?

      val textFile = sc.textFile("hdfs://...") // ler os dados do HDFS
      val counts = textFile.flatMap(line => line.split(" ")) // o split usa os  " " como delimitador para criar uma coleção
      .map(word => (word, 1)) // mapeamento de cadas palavar junto com sua ocorrencia, isso vai gerar um mapa de plavras onde a chave é a pŕopria palavra, e o valor será a quantidade corresponde a palavra
      .reduceByKey(_ + _)// agrupa e soma, ou seja um agrupamento de palavras com sua quantidade de ocorrencias.
      counts.saveAsTextFile("hdfs://...")// salva o agrupamento de palavras no hdfs
