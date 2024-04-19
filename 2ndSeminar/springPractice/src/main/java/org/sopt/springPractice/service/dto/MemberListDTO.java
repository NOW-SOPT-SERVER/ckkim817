package org.sopt.springPractice.service.dto;

import java.util.Collections;
import java.util.List;
import org.sopt.springPractice.domain.Member;

public record MemberListDTO(
        List<Member> members
) {
    public static MemberListDTO create(List<Member> originalMembers) {
        List<Member> unmodifiableMembers = Collections.unmodifiableList(originalMembers);

        return new MemberListDTO(unmodifiableMembers);
    }
}
