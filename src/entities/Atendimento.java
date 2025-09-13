package entities;

import java.time.Instant;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Atendimento {

	private UUID id;
	private String nomeCliente;
	private Instant dataHora;
	private String pergunta;
	private String resposta;
	
}
