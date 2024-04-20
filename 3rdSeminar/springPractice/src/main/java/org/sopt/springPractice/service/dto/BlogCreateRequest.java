package org.sopt.springPractice.service.dto;

public record BlogCreateRequest(
        String title,
        String description
) {
}
