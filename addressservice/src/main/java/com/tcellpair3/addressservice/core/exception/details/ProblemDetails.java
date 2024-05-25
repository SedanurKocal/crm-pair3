package com.tcellpair3.addressservice.core.exception.details;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ProblemDetails {
    private String title;
    private String detail;
}