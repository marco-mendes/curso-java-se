Você irá implementar um mecanismo de análise de risco de clientes.
Para isso você irá usar um instrumento chamado de score de crédito.

O score acima de 700 é classificado como OTIMO.
O score entre 300 e 699 é classificado como BOM.
O score abaixo de 300 é classificado como RUIM.

O score irá variar de acordo com as seguintes regras:
  - O valor inicial do score de crédito é de 500 pontos
  - Cada uso do cheque especial gera uma perda de 20 pontos no score
  - Aplicações maiores que 1000 reais nas contas do banco geram um ganho de 10 pontos no score
  - A compra de Titulo de Capitalização gera um ganho de 10 pontos no score.
  - Cheques devolvidos geram uma perda de 200 pontos no score.
  
Implemente um utilitário que calcule o score de crédito de um determinado cliente. Para isso use a metodologia BDD, estudada anteriormente.



   
  