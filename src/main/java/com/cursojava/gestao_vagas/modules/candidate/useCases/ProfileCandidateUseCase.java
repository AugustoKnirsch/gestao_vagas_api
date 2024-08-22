package com.cursojava.gestao_vagas.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cursojava.gestao_vagas.exceptions.UserNotFoundException;
import com.cursojava.gestao_vagas.modules.candidate.CandidateRepository;
import com.cursojava.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;

@Service
public class ProfileCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;
    
    public ProfileCandidateResponseDTO execute(UUID idCandidate) {
        var candidate = this.candidateRepository.findById(idCandidate)
            .orElseThrow(() -> {
                throw new UserNotFoundException();
            });
        
        var candidateDTO = ProfileCandidateResponseDTO.builder()
            .description(candidate.getDescription())
            .userName(candidate.getUserName())
            .email(candidate.getEmail())
            .name(candidate.getName())
            .id(candidate.getId())
            .build();
        return candidateDTO;

    }
    
}
