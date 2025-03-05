package co.edu.uniandes.dse.parcialprueba.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import jakarta.transaction.Transactional;
import co.edu.uniandes.dse.parcialprueba.entities.ConsultaMedicaEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@DataJpaTest
@Transactional
@Import({ PacienteService.class, ConsultaMedicaService.class})
public class ConsultaMedicaServiceTest  {
    
    @Autowired
	private ConsultaMedicaService consultaMedicaService;
	@Autowired
	private TestEntityManager entityManager;
    private PodamFactory factory = new PodamFactoryImpl();
    
    @BeforeEach
	void setUp() {
		clearData();
    }

    private void clearData() {
        entityManager.getEntityManager().createQuery("delete from ConsultaMedicaEntity");
		
	}

    @Test
    void createConsultaTest() throws EntityNotFoundException, IllegalOperationException {
        ConsultaMedicaEntity result = consultaMedicaService.createConsulta();
        assertNotNull(result);
    } 

}
