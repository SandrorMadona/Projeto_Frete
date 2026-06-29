# 🚚 API de Faturamento e Gestão de Fretes

Um sistema back-end robusto (API RESTful) desenvolvido em Java e Spring Boot para precificação dinâmica, gestão de dobras de turnos e cálculo automatizado de lucro líquido em operações logísticas diárias.

## 📌 O Problema Resolvido

Trabalho como motorista fazendo entregas e percebi que Operações de transporte logístico frequentemente sofrem com planilhas manuais e erros no cálculo de diárias, especialmente quando há dobras de turnos (manhã, tarde e noite) e taxas extras por rotas específicas. Esta API centraliza a regra de negócio no servidor, blindando os cálculos contra erros de front-end e automatizando o balanço financeiro diário.

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
   git clone [https://github.com/SEU-USUARIO/nome-do-repositorio.git](https://github.com/SEU-USUARIO/nome-do-repositorio.git)