package com.dh.clinicadental.dao;

import com.dh.clinicadental.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

    @Query("from AppUser u where u.name = :name")
    Optional<AppUser> getUserByName(@Param("name") String name);
}
