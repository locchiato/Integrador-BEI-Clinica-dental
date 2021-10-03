package com.dh.clinicadental.repository;


import com.dh.clinicadental.dao.IPacienteRepository;
import com.dh.clinicadental.dao.UserRepository;
import com.dh.clinicadental.model.AppUser;
import com.dh.clinicadental.model.Paciente;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repository;

    @Test
    public void save_StoresRecord_WhenRecordIsValid() {

        final AppUser expected = new AppUser();
        expected.setName(randomUUID().toString());
        expected.setUsername(randomUUID().toString());
        expected.setPassword(randomUUID().toString());

        final AppUser saved = repository.save(expected);

        final AppUser actual = entityManager.find(AppUser.class, saved.getId());

        assertThat(actual).isEqualTo(expected);
    }
}
