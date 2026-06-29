Markdown

# 🚚 API de Faturamento e Gestão de Fretes

Um sistema back-end robusto (API RESTful) desenvolvido em Java e Spring Boot para precificação dinâmica, gestão de dobras de turnos e cálculo automatizado de lucro líquido em operações logísticas diárias.

## 📌 O Problema Resolvido
Operações de transporte logístico frequentemente sofrem com planilhas manuais e erros no cálculo de diárias, especialmente quando há dobras de turnos (manhã, tarde e noite) e taxas extras por rotas específicas. Esta API centraliza a regra de negócio no servidor, blindando os cálculos contra erros de front-end e automatizando o balanço financeiro diário.

## 🛠 Tecnologias Utilizadas
* **Java 17**
* **Spring Boot 3** (Web, Data JPA, Validation)
* **PostgreSQL** (Banco de Dados Relacional)
* **Hibernate** (Mapeamento Objeto-Relacional)
* **Lombok** (Redução de Boilerplate)
* **Postman** (Testes de Integração e Endpoints)

## ⚙️ Funcionalidades e Regras de Negócio
* **Precificação Dinâmica (Upsert):** Tabela de preços configurável para turnos operacionais (AM, PM, SD) e taxas adicionais (SDD).
* **Gestão de Custos Fixos:** Cadastro e controle de despesas operacionais (financiamento, óleo, reserva).
* **Motor de Cálculo Automático:** O sistema recebe apenas os "fatos" (turnos operados e gastos com combustível) e calcula internamente o Faturamento Bruto através de condicionais de dobras (`isDobra`) e bônus.
* **Segurança de Transação:** Bloqueio de inserção de fretes caso os parâmetros financeiros base não estejam configurados (`orElseThrow`).

## 🚀 Como Executar o Projeto

1. Clone este repositório:
   ```bash
   git clone [https://github.com/SandrorMadona/Projeto_Frete.git](https://github.com/SandrorMadona/Projeto_Frete.git)

    Configure o banco de dados no arquivo application.properties:
    Properties

    spring.datasource.url=jdbc:postgresql://localhost:5432/seu_banco
    spring.datasource.username=seu_usuario
    spring.datasource.password=sua_senha
    spring.jpa.hibernate.ddl-auto=update

    Rode a aplicação pela sua IDE ou via Maven:
    Bash

    mvn spring-boot:run

📡 Endpoints Principais
Tabela de Preços e Custos

    POST /api/precos - Atualiza a tabela de preços das rotas.

    GET /api/precos - Consulta a tabela vigente.

    POST /api/fixos - Configura os custos fixos mensais do veículo.

Operação Diária (Fretes)

    POST /api/fretes - Registra um novo dia de trabalho (calcula o faturamento automaticamente).
    JSON

    // Exemplo de Request
    {
      "dataServico": "2026-06-29",
      "turnoRealizado": [
        { "turno": "AM", "sdd": true },
        { "turno": "PM", "sdd": false }
      ],
      "gasto": 50.00
    }

    GET /api/fretes - Lista todo o histórico ordenado por data e ID.

    DELETE /api/fretes/{id} - Estorna um lançamento incorreto.

👨‍💻 Sobre o Autor

Estudante do último semestre de TI focado no ecossistema Full Stack (Java e React). Possuo experiência prática no desenvolvimento de APIs RESTful, modelagem de banco de dados e resolução de regras de negócio através de código. Busco vagas de Estágio ou Júnior para iniciar minha carreira na programação, trazendo raciocínio lógico apurado e visão orientada a resultados.

    LinkedIn: Sandro Rogerio Madona Filho
https://www.linkedin.com/in/sandro-rogerio-madona-filho/

    E-mail: sandrormadona@gmail.com
