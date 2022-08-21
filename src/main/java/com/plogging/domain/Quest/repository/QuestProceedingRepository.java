package com.plogging.domain.Quest.repository;

import com.plogging.domain.Quest.entity.UserQuestProceeding;
import com.plogging.domain.User.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestProceedingRepository extends JpaRepository<UserQuestProceeding, Long> {
    Page<UserQuestProceeding> findAllByUser(Pageable pageable, User user);
}
