package org.sopt.springPractice.repository;

import org.sopt.springPractice.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
