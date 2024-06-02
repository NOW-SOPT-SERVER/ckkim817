package org.sopt.springPractice.auth.redis.repository;

import java.util.Optional;
import org.sopt.springPractice.auth.redis.domain.Token;
import org.springframework.data.repository.CrudRepository;

public interface RedisTokenRepository extends CrudRepository<Token, Long> {
    Optional<Token> findByRefreshToken(final String refreshToken);

    Optional<Token> findById(final Long id);
}
