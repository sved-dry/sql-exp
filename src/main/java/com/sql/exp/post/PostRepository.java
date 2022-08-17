package com.sql.exp.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findPostById(Long id);

    @Query(
            """
            SELECT n FROM Post n
            WHERE n.topicId=:topicId
"""

    )
    List<Post> findPostsByTopicId(@Param("topicId") Long topicId);
}
