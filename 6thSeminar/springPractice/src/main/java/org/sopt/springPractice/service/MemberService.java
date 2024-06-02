package org.sopt.springPractice.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.sopt.springPractice.auth.UserAuthentication;
import org.sopt.springPractice.common.dto.ErrorMessage;
import org.sopt.springPractice.common.jwt.JwtTokenProvider;
import org.sopt.springPractice.domain.Member;
import org.sopt.springPractice.exception.NotFoundException;
import org.sopt.springPractice.repository.MemberRepository;
import org.sopt.springPractice.service.dto.MemberCreateDTO;
import org.sopt.springPractice.service.dto.MemberFindDTO;
import org.sopt.springPractice.service.dto.MemberListDTO;
import org.sopt.springPractice.service.dto.UserJoinResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public UserJoinResponse createMember(
            MemberCreateDTO memberCreateDTO
    ) {
        Member member = memberRepository.save(
                Member.create(memberCreateDTO.name(), memberCreateDTO.part(), memberCreateDTO.age())
        );
        Long memberId = member.getId();
        String accessToken = jwtTokenProvider.issueAccessToken(
                UserAuthentication.createUserAuthentication(memberId)
        );

        return UserJoinResponse.of(accessToken, memberId.toString());
    }

    public Member findById(
            Long memberId
    ) {
        return memberRepository.findById(memberId).orElseThrow(
                () -> new NotFoundException(ErrorMessage.MEMBER_NOT_FOUND)
        );
    }

    public MemberFindDTO findMemberById(
            Long memberId
    ) {
        return MemberFindDTO.of(memberRepository.findById(memberId).orElseThrow(
                () -> new EntityNotFoundException("ID에 해당하는 사용자가 존재하지 않습니다."))
        );
    }

    @Transactional
    public void deleteMemberById(
            Long memberId
    ) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new EntityNotFoundException("ID에 해당하는 사용자가 존재하지 않습니다.")
        );
        memberRepository.delete(member);
    }

    public MemberListDTO findMemberList() {
        return MemberListDTO.create(memberRepository.findAll());
    }
}
