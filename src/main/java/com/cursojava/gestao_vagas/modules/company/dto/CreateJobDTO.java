package com.cursojava.gestao_vagas.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.Data;

@Data
public class CreateJobDTO {

    @Schema(example = "Vaga para Desenvolvedor Junior", requiredMode = RequiredMode.REQUIRED)
    private String description;
    
    @Schema(example = "Plano de Saude", requiredMode = RequiredMode.REQUIRED)
    private String benefits;

    @Schema(example = "Junior", requiredMode = RequiredMode.REQUIRED)    
    private String level;
    
}
