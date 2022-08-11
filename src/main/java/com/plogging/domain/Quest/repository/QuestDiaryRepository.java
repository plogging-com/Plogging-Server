package com.plogging.domain.Quest.repository;

import com.plogging.domain.Quest.entity.UserQuestDiary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestDiaryRepository extends JpaRepository<UserQuestDiary, Long> {
}
