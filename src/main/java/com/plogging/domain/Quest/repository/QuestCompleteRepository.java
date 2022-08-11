package com.plogging.domain.Quest.repository;

import com.plogging.domain.Quest.entity.UserQuestComplete;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestCompleteRepository extends JpaRepository<UserQuestComplete, Long> {
}
