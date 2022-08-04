package com.plogging.domain.Quest.repository;

import com.plogging.domain.Quest.entity.Quest;
import com.plogging.domain.Quest.entity.UserQuestDiary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserQuestDiaryRepository extends JpaRepository<UserQuestDiary, Long> {
}
