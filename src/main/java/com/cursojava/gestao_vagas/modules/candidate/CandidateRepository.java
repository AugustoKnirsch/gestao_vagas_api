package com.cursojava.gestao_vagas.modules.candidate;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID> {
    
    Optional<CandidateEntity> findByUserNameOrEmail(String userName, String email);
    Optional<CandidateEntity> findByUserName(String userName);


}
