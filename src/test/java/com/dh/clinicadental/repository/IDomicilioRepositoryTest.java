package com.dh.clinicadental.repository;

import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;

import com.dh.clinicadental.dao.IDomicilioRepository;
import com.dh.clinicadental.model.Domicilio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class IDomicilioRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private IDomicilioRepository repository;

    @Test
    public void save_StoresRecord_WhenRecordIsValid() {

        final Domicilio expected = new Domicilio();
        expected.setNumero(randomUUID().toString());
        expected.setCalle(randomUUID().toString());

        final Domicilio saved = repository.save(expected);

        final Domicilio actual = entityManager.find(Domicilio.class, saved.getId());

        assertThat(actual).isEqualTo(expected);
    }
}
