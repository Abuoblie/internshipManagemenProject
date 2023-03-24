package com.internship.management.repositories;

import com.internship.management.models.entities.Intern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface InternUserRepository extends JpaRepository<Intern,Long> {
    Optional<Intern>findByEmail(String email);
    Optional<Intern>deleteByEmail(String email);





}
