package co.edu.uniandes.dse.parcialprueba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialprueba.entities.PacienteEntity;
import co.edu.uniandes.dse.parcialprueba.repositories.PacienteRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;



@Service
@Slf4j
public class PacienteService {
    
    @Autowired
    private PacienteRepository pacienteRepository;


    @Transactional
    public PacienteEntity createPaciente(PacienteEntity paciente) {
        log.info("Creating a new Paciente");
        log.info("Termina proceso de creación de un Paciente");
        return pacienteRepository.save(paciente);
    }

}
