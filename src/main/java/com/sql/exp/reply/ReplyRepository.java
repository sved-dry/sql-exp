package com.sql.exp.reply;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {
    Optional<Reply> findReplyById(Long id);

    @Query(
            """
            SELECT n FROM Reply n
            WHERE n.topicId=:topicId
"""

    )
    List<Reply> findRepliesTopicId(@Param("topicId") Long topicId);
}
