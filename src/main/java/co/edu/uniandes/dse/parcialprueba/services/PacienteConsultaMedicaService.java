package co.edu.uniandes.dse.parcialprueba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import co.edu.uniandes.dse.parcialprueba.entities.PacienteEntity;
import co.edu.uniandes.dse.parcialprueba.repositories.ConsultaMedicaRepository;
import co.edu.uniandes.dse.parcialprueba.repositories.PacienteRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import jakarta.transaction.Transactional;
import co.edu.uniandes.dse.parcialprueba.entities.ConsultaMedicaEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;



@Slf4j
@Data
@Service
public class PacienteConsultaMedicaService {

    @Autowired
     private PacienteRepository pacienteRepository;
 
     @Autowired
     private ConsultaMedicaRepository consultaMedicaRepository;
 

    @Transactional
    public ConsultaMedicaEntity addConsulta(Long Id_Paciente,Long Id_consulta)  throws EntityNotFoundException, IllegalOperationException{
        PacienteEntity paciente = pacienteRepository.findById(Id_Paciente).orElseThrow(() -> new EntityNotFoundException("El paciente no existe"));
        log.info("Creating a new Paciente");
        log.info("Termina proceso de creación de un Paciente");
        ConsultaMedicaEntity consulta = consultaMedicaRepository.findById(Id_consulta).orElseThrow(() -> new EntityNotFoundException("La consulta no existe"));
        log.info("Creating new Consulta");
        log.info("Termina proceso de creación de una Consulta");
        
        List<ConsultaMedicaEntity> consultas = paciente.getConsultas();

        for(int i = 0; i < consultas.size(); i++){
            if (consultas.get(i).getFecha().equals(consulta.getFecha())) {
                throw new IllegalOperationException("El paciente ya tiene una consulta programada para esa fecha");
            }
        }
        consulta.setPaciente(paciente);
        return consultaMedicaRepository.save(consulta);
    }


    @Transactional
    public  List<ConsultaMedicaEntity> getConsultasProgramadas(Long Id_Paciente) throws EntityNotFoundException{
        PacienteEntity paciente = pacienteRepository.findById(Id_Paciente).orElseThrow(() -> new EntityNotFoundException("El paciente no existe"));
        log.info("Creating a new Paciente");
        log.info("Termina proceso de creación de un Paciente");
        List<ConsultaMedicaEntity> consultas = paciente.getConsultas();
        List<ConsultaMedicaEntity> consultasNuevas = new ArrayList<ConsultaMedicaEntity>(); 

        for(int i = 0; i < consultas.size(); i++){
            if (consultas.get(i).getFecha().after(new Date())) {
                consultasNuevas.add(consultas.get(i));
            }
        }

        return consultasNuevas;
    }
}
