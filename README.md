# 🤖 Atendente Virtual de Suporte de TI

Este projeto é uma aplicação Java simples que utiliza a [API da OpenAI](https://platform.openai.com/) para criar um **atendente virtual com inteligência artificial**, capaz de responder apenas perguntas relacionadas a **suporte de TI** de forma cordial e personalizada.  

As respostas geradas são armazenadas em um banco de dados **PostgreSQL**, que roda em um container Docker.

---

## 🚀 Funcionalidades
- Conecta-se à API da OpenAI (`gpt-4o`) para geração de respostas.
- Atua como um atendente virtual focado em suporte de computadores.
- Armazena as interações no banco PostgreSQL.
- Usa `docker-compose` para simplificar a execução do banco de dados.

---

## 📦 Requisitos
- [Java 17+](https://adoptium.net/)  
- [Maven](https://maven.apache.org/) (ou sua IDE favorita configurada para Maven)  
- [Docker](https://www.docker.com/) e [Docker Compose](https://docs.docker.com/compose/)  

---

## 🛠️ Como executar

### 1. Clonar o repositório
```bash
git clone https://github.com/seu-usuario/seu-repositorio.git
cd seu-repositorio

Subir o banco de dados com Docker

Na raiz do projeto existe um arquivo docker-compose.yml. Execute:
```docker-compose up -d
