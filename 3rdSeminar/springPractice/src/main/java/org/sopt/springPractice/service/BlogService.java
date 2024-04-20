package org.sopt.springPractice.service;

import lombok.RequiredArgsConstructor;
import org.sopt.springPractice.common.dto.ErrorMessage;
import org.sopt.springPractice.domain.Blog;
import org.sopt.springPractice.domain.Member;
import org.sopt.springPractice.exception.NotFoundException;
import org.sopt.springPractice.repository.BlogRepository;
import org.sopt.springPractice.service.dto.BlogCreateRequest;
import org.sopt.springPractice.service.dto.BlogTitleUpdateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;
    private final MemberService memberService;

    public String create(Long memberId, BlogCreateRequest blogCreateRequest) {
        Member member = memberService.findById(memberId);
        Blog blog = blogRepository.save(Blog.create(member, blogCreateRequest));

        return blog.getId().toString();
    }

    public Blog findBlogById(Long blogId) {
        return blogRepository.findById(blogId).orElseThrow(
                () -> new NotFoundException(ErrorMessage.BLOG_NOT_FOUND)
        );
    }

    @Transactional
    public void updateTitle(Long blogId, BlogTitleUpdateRequest blogTitleUpdateRequest) {
        Blog blog = findBlogById(blogId);
        blog.updateTitle(blogTitleUpdateRequest);
    }
}
