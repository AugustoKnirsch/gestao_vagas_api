package com.cursojava.gestao_vagas.modules.candidate.useCases;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.assertj.core.api.PredicateAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.cursojava.gestao_vagas.exceptions.JobNotFoundException;
import com.cursojava.gestao_vagas.exceptions.UserNotFoundException;
import com.cursojava.gestao_vagas.modules.candidate.CandidateRepository;
import com.cursojava.gestao_vagas.modules.company.repositories.JobRepository;
import java.util.UUID;

//import org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {

    @InjectMocks
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private JobRepository jobRepository;

    @Test
    @DisplayName("Should not be able to apply job with candidate not found")
    public void should_not_be_able_to_apply_job_with_candidate_not_found() {
        try {
            applyJobCandidateUseCase.execute(null, null);            
        } catch (Exception e) {            
          //  assertThat(e).isInstanceOf(UserNotFoundException.class)
        }        
    }

    @Test
    public void should_not_be_able_to_apply_job_with_job_not_found() {
        var idCandidate = UUID.randomUUID();
        when(candidateRepository.findById(idCandidate)).thenReturn(any());

        try {
            applyJobCandidateUseCase.execute(idCandidate, null);            
        } catch (Exception e) {
            //assertThat(e).isInstanceOf(JobNotFoundException.class)
        }  
    }
    
}
