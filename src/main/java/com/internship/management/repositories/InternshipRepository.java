package com.internship.management.repositories;
import com.internship.management.models.entities.Internship;
import com.internship.management.models.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
public interface InternshipRepository extends JpaRepository<Internship, Long> {
   List<Internship>findAllByExpiringDateIsLessThanEqualAndStatus(LocalDate expiringDate, Status status);
   List<Internship>findAllByStatus(Status status);
   List<Internship>findAllByEnterprise_Email(String email);
   Optional<Internship> findByIdAndStatus(Long aLong, String status);
}