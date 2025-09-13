package repositories;

import java.sql.DriverManager;
import java.sql.Timestamp;

import entities.Atendimento;

public class AtendimentoRepository {
	
	public void salvarAtendimento(Atendimento atendimento) {
		
		try {
			var connection = DriverManager.getConnection("jdbc:postgresql://localhost:5436/bd_atendimentos", "user_rafa", "Admin123");
			
			var statement = connection.prepareStatement("INSERT INTO atendimento(id, nomecliente, datahora, pergunta, resposta) VALUES(?,?,?,?,?)");	
			
			statement.setObject(1, atendimento.getId());
			statement.setString(2, atendimento.getNomeCliente());
			statement.setTimestamp(3, Timestamp.from(atendimento.getDataHora()));
			statement.setString(4, atendimento.getPergunta());
			statement.setString(5, atendimento.getResposta());
			statement.execute();
			
			connection.close();
			
			System.out.println("\nATENDIMENTO CADASTRADO COM SUCESSO!");
		}
		catch (Exception e) {
			System.out.println("\nFALHA: " + e.getMessage());
		}
	}

}
