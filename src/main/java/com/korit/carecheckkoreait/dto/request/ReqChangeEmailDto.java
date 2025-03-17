package com.korit.carecheckkoreait.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ReqChangeEmailDto {
    @Schema(description = "이메일", example = "test@gmail.com")
    private String email;
}
