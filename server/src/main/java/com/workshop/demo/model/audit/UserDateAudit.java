package com.workshop.demo.model.audit;

import org.springframework.data.annotation.CreatedBy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass
@Data
@JsonIgnoreProperties(value = { "createdBy" }, allowGetters = true)
public abstract class UserDateAudit {

    @CreatedBy
    @Column(updatable = false)
    private Long createdBy;
}