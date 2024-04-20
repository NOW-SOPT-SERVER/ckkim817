package org.sopt.springPractice.controller;

import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.sopt.springPractice.service.MemberService;
import org.sopt.springPractice.service.dto.MemberCreateDTO;
import org.sopt.springPractice.service.dto.MemberFindDTO;
import org.sopt.springPractice.service.dto.MemberListDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/member")
    public ResponseEntity<Void> createMember(
            @RequestBody MemberCreateDTO memberCreateDTO
    ) {
        return ResponseEntity.created(URI.create(memberService.createMember(memberCreateDTO))).build();
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<MemberFindDTO> findMemberById(
            @PathVariable Long memberId
    ) {
        return ResponseEntity.ok(memberService.findMemberById(memberId));
    }

    @DeleteMapping("/member/{memberId}")
    public ResponseEntity<Void> deleteMemberById(
            @PathVariable Long memberId
    ) {
        memberService.deleteMemberById(memberId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/members")
    public ResponseEntity<MemberListDTO> getMemberList() {
        return ResponseEntity.ok(memberService.findMemberList());
    }
}
