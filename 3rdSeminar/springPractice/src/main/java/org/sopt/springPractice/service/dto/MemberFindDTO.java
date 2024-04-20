package org.sopt.springPractice.service.dto;

import org.sopt.springPractice.domain.Member;
import org.sopt.springPractice.domain.Part;

public record MemberFindDTO(
        String name,
        Part part,
        int age
) {
    public static MemberFindDTO of(Member member) {
        return new MemberFindDTO(member.getName(), member.getPart(), member.getAge());
    }
}
