package org.sopt.springPractice.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.sopt.springPractice.domain.Member;
import org.sopt.springPractice.repository.MemberRepository;
import org.sopt.springPractice.service.dto.MemberCreateDTO;
import org.sopt.springPractice.service.dto.MemberFindDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public String createMember(
            MemberCreateDTO memberCreateDTO
    ) {
        Member member = Member.create(memberCreateDTO.name(), memberCreateDTO.part(), memberCreateDTO.age());
        memberRepository.save(member);

        return member.getId().toString();
    }

    public MemberFindDTO findMemberById(
            Long memberId
    ) {
        return MemberFindDTO.of(memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("ID에 해당하는 사용자가 존재하지 않습니다.")));
    }

    @Transactional
    public void deleteMemberById(
            Long memberId
    ) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("ID에 해당하는 사용자가 존재하지 않습니다."));
        memberRepository.delete(member);
    }
}
