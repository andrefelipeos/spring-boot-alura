package med.voll.api.medico;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import med.voll.api.endereco.Endereco;

@Table(name = "medicos")
@Entity(name = "Medico")
public class Medico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long identificador;

	private String nome;
	private String email;
	private String crm;

	@Enumerated(EnumType.STRING)
	private Especialidade especialidade;

	@Embedded
	private Endereco endereco;

}
