package com.plogging.domain.Quest.repository;

import com.plogging.domain.Quest.entity.Quest;
import com.plogging.domain.Quest.entity.UserQuestComplete;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserQuestCompleteRepository extends JpaRepository<UserQuestComplete, Long> {

    UserQuestComplete questCreate(UserQuestComplete quest);

    UserQuestComplete questUpdate(UserQuestComplete quest);

    void questDeleteById(Long id);


}