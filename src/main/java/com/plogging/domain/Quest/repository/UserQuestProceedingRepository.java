package com.plogging.domain.Quest.repository;

import com.plogging.domain.Quest.entity.Quest;
import com.plogging.domain.Quest.entity.UserQuestProceeding;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserQuestProceedingRepository extends JpaRepository<UserQuestProceeding, Long> {
}
