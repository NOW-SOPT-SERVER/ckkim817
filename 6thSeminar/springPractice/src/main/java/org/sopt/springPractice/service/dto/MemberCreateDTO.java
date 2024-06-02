package org.sopt.springPractice.service.dto;

import org.sopt.springPractice.domain.Part;

public record MemberCreateDTO(
        String name,
        Part part,
        int age
) {
}
