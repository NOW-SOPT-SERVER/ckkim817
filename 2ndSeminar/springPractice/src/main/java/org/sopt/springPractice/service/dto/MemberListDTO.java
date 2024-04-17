package org.sopt.springPractice.service.dto;

import java.util.List;
import org.sopt.springPractice.domain.Member;

public record MemberListDTO(
        List<Member> members
) {
    public static MemberListDTO create(List<Member> members) {
        return new MemberListDTO(members);
    }
}
