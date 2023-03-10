package med.voll.api.domain.consulta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;

@Service
public class AgendaDeConsultas {

	@Autowired
	private ConsultaRepository consultaRepository;

	@Autowired
	private MedicoRepository medicoRepository;

	@Autowired
	private PacienteRepository pacienteRepository;

	public void agendar(DadosAgendamentoConsulta dados) {
		if (!pacienteRepository.existsById(dados.idDoPaciente())) {
			throw new ValidacaoException("Identificador de paciente informado não existe");
		}

		if (dados.idDoMedico() != null && !medicoRepository.existsById(dados.idDoMedico())) {
			throw new ValidacaoException("Identificador de médico informado não existe");
		}

		var medico = medicoRepository.findById(dados.idDoMedico()).get();
		var paciente = pacienteRepository.findById(dados.idDoPaciente()).get();
		var consulta = new Consulta(null, medico, paciente, dados.horarioComData());
		consultaRepository.save(consulta);
	}

}
