package org.sopt.springPractice.service;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.sopt.springPractice.common.dto.ErrorMessage;
import org.sopt.springPractice.domain.Blog;
import org.sopt.springPractice.domain.Member;
import org.sopt.springPractice.exception.NotFoundException;
import org.sopt.springPractice.external.S3Service;
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
    private final S3Service s3Service;
    private static final String BLOG_S3_UPLOAD_FOLDER = "blog/";

    public String create(Long memberId, BlogCreateRequest createRequest) {
        Member member = memberService.findById(memberId);
        try {
            Blog blog = blogRepository.save(
                    Blog.create(member, createRequest.title(), createRequest.description(),
                            s3Service.uploadImage(BLOG_S3_UPLOAD_FOLDER, createRequest.image())));

            return blog.getId().toString();
        } catch (RuntimeException | IOException e) {
            throw new RuntimeException(e.getMessage());
        }
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
