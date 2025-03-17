package com.korit.carecheckkoreait.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "patient 검색 DTO")
public class ReqSearchChartDto {

    @Schema(description = "patient명")
    private Integer patientId;
}
