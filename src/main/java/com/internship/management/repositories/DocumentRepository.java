package com.internship.management.repositories;

import com.internship.management.models.entities.Document;
import com.internship.management.models.entities.Intern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
     Optional<Document>findByTypeAndIntern_Email(String type,String email);
}