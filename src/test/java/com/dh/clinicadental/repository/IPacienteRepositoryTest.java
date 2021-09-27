package com.dh.clinicadental.repository;

import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;

import com.dh.clinicadental.dao.IPacienteRepository;
import com.dh.clinicadental.model.Paciente;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class IPacienteRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private IPacienteRepository repository;

    @Test
    public void save_StoresRecord_WhenRecordIsValid() {

        final Paciente expected = new Paciente();
        expected.setNombre(randomUUID().toString());
        expected.setApellido(randomUUID().toString());

        final Paciente saved = repository.save(expected);

        final Paciente actual = entityManager.find(Paciente.class, saved.getId());

        assertThat(actual).isEqualTo(expected);
    }
}
