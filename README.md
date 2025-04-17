# Mergulho em Java: Três Desafios de Desenvolvimento

### João Marcelo Furtado Romero (RM555199)

Este repositório apresenta uma coleção de **três sistemas distintos**, construídos utilizando a linguagem de programação **Java**. Cada projeto explora um domínio de aplicação específico, demonstrando a aplicação de princípios de **Programação Orientada a Objetos (POO)**, a implementação de **interfaces** para promover o desacoplamento e a robustez do código, e a garantia da qualidade através de **testes unitários**.

## Visão Geral dos Projetos

A estrutura do repositório é organizada da seguinte forma:

Cada pasta `challenge` tem o seu próprio desafio

Dentro encontramos `src` que terá `main` e `test` como diretórios diretos.

Dentro de `test` encontraremos classes teste da aplicação

Dentro de `main` encontraremos pastas como a `model` que terá nossas entidades, a pasta `ui` que terá como o usuário irá interagir com a aplicação

Teremos também o arquivo `Main.java` para rodar a apliação.

Não podemos nos esquecer também das pastas `interfaces` dentro de `model` e `ui` da qual dentro estará localizado os arquivos de *interface* para serem implementadas, ditando o comportamento com um contrato explícito. 


## Detalhes de Cada Desafio

### ⚽ Desafio 01: Maestria em Campo - Gestão de Campeonato de Futebol Feminino

**Propósito:** Desenvolver um sistema completo para a administração de campeonatos de futebol feminino, desde o registro de participantes até a apuração dos resultados.

**Funcionalidades Chave:**

* **Gerenciamento de Entidades:** Cadastro detalhado de times e suas respectivas jogadoras.
* **Acompanhamento de Partidas:** Registro de jogos, incluindo a anotação dos placares.
* **Lógica de Pontuação:** Implementação automática do sistema de pontos (3 para vitória, 1 para empate, 0 para derrota).
* **Visualização da Competição:** Geração dinâmica da tabela de classificação do campeonato.

### ♿ Desafio 02: Inclusão em Foco - Cadastro de Pessoas com Necessidades Especiais

**Objetivo:** Construir um sistema para o cadastro e o acompanhamento de pessoas com necessidades especiais, visando o gerenciamento de seus atendimentos.

**Funcionalidades Centrais:**

* **Registro Detalhado:** Cadastro de informações de pessoas com deficiência, incluindo o tipo e o grau da necessidade especial.
* **Gestão de Atendimentos:** Registro dos atendimentos profissionais realizados para cada pessoa cadastrada.
* **Análise e Filtragem:** Implementação de filtros para buscar pessoas por tipo ou grau de deficiência.
* **Geração de Insights:** Produção de relatórios sumarizados dos atendimentos realizados.

### 🚗 Desafio 03: Rodovias Inteligentes - Sistema de Controle de Pedágios em SP

**Finalidade:** Desenvolver um simulador para o controle de veículos em praças de pedágio localizadas no estado de São Paulo, com foco no cálculo automatizado de tarifas.

**Funcionalidades Essenciais:**

* **Gerenciamento de Praças:** Cadastro das diferentes praças de pedágio.
* **Registro de Passagens:** Gravação da passagem de cada veículo pelas praças.
* **Cálculo de Tarifas:** Determinação automática do valor da tarifa com base no tipo de veículo
* **Relatórios Financeiros:** Geração de relatórios de arrecadação por praça e período.

