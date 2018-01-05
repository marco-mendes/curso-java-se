#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Analise do Risco

  @tag2
  Scenario Outline: Classificacao do Risco
    Given Eu quero avaliar a <classicacao de risco> 
    When Eu entro o <valor> do score de credito
    Then Eu verifico o <status> do score

    Examples: 
      | Classificacao do Riscos  | Valor | Status  |
      | 1  |   >= 700          | OTIMO |
      | 2  |   >= 300 && < 700 | BOM   |
      | 3  |   <300            | RUIM   |
  

  @tag1
  Scenario: Inicializacao do risco de credito
    Given Eu crio um cliente
    And Defino o risco Inicial
    When Eu avalio o risco
    Then o valor deve ser igual a 500
 

