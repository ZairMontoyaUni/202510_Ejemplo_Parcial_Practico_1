package co.edu.uniandes.dse.parcialprueba.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import co.edu.uniandes.dse.parcialprueba.entities.ConsultaMedicaEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.ConsultaMedicaRepository;

@Service
@Slf4j
public class ConsultaMedicaService {
    
    @Autowired
    private ConsultaMedicaRepository consultaMedicaRepository;

    @Transactional
    public ConsultaMedicaEntity createConsulta(ConsultaMedicaEntity consultaMedica) throws IllegalOperationException{
        log.info("Creating a new ConsultaMedica");


        if(!(consultaMedica.getFecha().after(new Date()))) {
            throw new IllegalOperationException("La fecha de la consulta no puede ser anterior a la fecha actual");
        }

        log.info("Termina proceso de creación de un ConsultaMedica");
        return consultaMedicaRepository.save(consultaMedica);
    }


}
