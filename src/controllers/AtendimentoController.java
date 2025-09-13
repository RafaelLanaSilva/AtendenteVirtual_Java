package controllers;

import java.time.Instant;
import java.util.Scanner;
import java.util.UUID;

import entities.Atendimento;
import repositories.AtendimentoRepository;
import services.OpenAIService;

public class AtendimentoController {
	
	private Scanner scanner = new Scanner(System.in);
	
	public void realizarAtendimento() {
		System.out.println("\n*** ATENDIMENTO DE SUPORTE ***\n");
		
		var atendimento = new Atendimento();
		
		atendimento.setId(UUID.randomUUID());
		atendimento.setDataHora(Instant.now());
		
		System.out.print("INFORME SOU NOME......:");
		atendimento.setNomeCliente(scanner.nextLine());
		
		System.out.print("FAÇA A SUA PERGUNTA...:");
		atendimento.setPergunta(scanner.nextLine());
		
		//acessando a camada de serviço
		var openAIService = new OpenAIService();
		var resposta = openAIService.sendMessage("Meu nome é: "
		+ atendimento.getNomeCliente() + ", " + atendimento.getPergunta());
		
		//imprimir a resposta
		System.out.println("\nRESPOSTA:");
		System.out.println(resposta);
		
		//armazenar a resposta na entidade
		atendimento.setResposta(resposta);
		
		//gravando no banco de dados
		var atendimentoRepository = new AtendimentoRepository();
		atendimentoRepository.salvarAtendimento(atendimento);
	}

}
