package com.plogging.domain.Quest.repository;

import com.plogging.domain.Quest.entity.Quest;
import com.plogging.domain.Quest.entity.UserQuestComplete;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface QuestRepository extends JpaRepository<Quest, Long> {
    Optional<Quest> findByName(String name);
}
