package org.sopt.springPractice.repository;

import org.sopt.springPractice.domain.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Long> {
}
