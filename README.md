<h1 align = "center">{BACK-END}</h1>
 <h1 align = "center"> Projeto O-Share </h1>
## :page_facing_up: Sobre o projeto
O-Share, uma plataforma que é a ponte entre profissionais de todos os perfis, com desafios e propostas diferentes, que desejam interagir para trocar experiências e conhecimento.
De forma simples, os colaboradores conseguem procurar por perfis de acordo com as especificações que pretendem, agendar uma mentoria e também se disponibilizarem para serem mentores.

## :mortar_board: Desafio Hackathon
Projeto desenvolvido para o programa de formação FCamara [{Season 3}](https://digital.fcamara.com.br/programadeformacao) 
- Período: 01 a 15 de abril/2022

## :hammer_and_wrench: Tecnologias utilizadas
### Back-end
- Spring Boot (Framework)
- Java (Linguagem)
- PostgreSQL (Banco de Dados)
- [Swagger (Documentação API)](https://technicalshare-api.herokuapp.com/swagger-ui/)
- [Heroku (Deploy)](https://dashboard.heroku.com/apps/technicalshare-api)

<h1 align = "center">O-Share é uma API REST</h1>
A aplicação foi escrita em linguagem Java e se apropria da arquitetura REST para o desenvolvimento padronizado e escalonável. Tal característica é atribuida em função da implementação do protocolo HTTP e seus quatro principais métodos (GET, POST, PUT e DELETE), utiilzados para a realização de requisições (request) e obtenção de respostas (responses) do servidor. A escolha dessa arquitetura é justificada por sua interoperabilidade simplificada e funcional, que permite a conexão com o Front-end. 

# <h1 align = "center"> Detalhando a aplicação em suas classes</h1>
## Model – Responsável pela criação das tabelas e manipulação de dados 
### UsuarioModel
Atributo         | Descrição
-----------------|-----------
id               | Número identificador único
nome             | Nome completo
funcao           | Função do colaborador
habilidade       | Habilidade do mentor
telefone         | Telefone do mentor
email            | E-mail 
senha            | Senha
foto             | Foto de perfil
sobremim         | Descrição profissional / Experiências
dataNascimento   | Data de Nascimento 
dataCriacaoConta | Data de criação da conta 

### UsuarioPostagem
Atributo         | Descrição
-----------------|-----------
id               | Número identificador único
titulo           | Título da postagem
texto            | Informações gerais sobre a mentoria
foto             | Foto associada a mentoria
date             | Data da postagem

Relacionamento entre tabelas:
- UsuarioModel  → UsuarioPostagem (OneToMany): Um usuário pode realizar “n” postagens
-	UsuarioPostagem → UsuarioModel (ManyToOne): Muitas postagens podem estar vinculadas a somente um usuário. 

## Controller – Controle de processamento de requisições 
-	Construção de Endpoints 

## Security - Implementações de Segurança
-	Criptografia de senhas de acesso
-	Token de autenticação

## Service
-	Validação de usuários cadastrados e não cadastrados para impedir a duplicidade


## Elaborado por: [_**Victor Hugo Costa de Souza**_](https://www.linkedin.com/in/victorhcsouza/)


<h1 align = "center">Agradecemos ao grupo FCamara pela rica e valiosa oportunidade :orange_heart:</h1>
