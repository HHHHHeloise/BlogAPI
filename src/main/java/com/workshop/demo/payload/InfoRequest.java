package com.workshop.demo.payload;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class InfoRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String name;
}