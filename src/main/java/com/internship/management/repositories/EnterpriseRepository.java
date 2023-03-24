package com.internship.management.repositories;

import com.internship.management.models.entities.Enterprise;
import com.internship.management.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {
    Optional<Enterprise> findByEmail(String email);


}