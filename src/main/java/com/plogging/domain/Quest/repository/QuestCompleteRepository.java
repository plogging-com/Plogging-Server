package com.plogging.domain.Quest.repository;

import com.plogging.domain.Quest.entity.UserQuestComplete;
import com.plogging.domain.User.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestCompleteRepository extends JpaRepository<UserQuestComplete, Long> {
    Page<UserQuestComplete> findAllByUser(Pageable pageable, User user);
}
