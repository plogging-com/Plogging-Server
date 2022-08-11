package com.plogging.domain.Quest.dto.quest.response;

import com.plogging.domain.Quest.entity.Quest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuestRes {

    private String name;
    private String imageUrl;

    public static QuestRes create(Quest quest) {
        QuestRes questRes = new QuestRes();
        questRes.name = quest.getName();
        questRes.imageUrl = quest.getPhoto(); //TODO
        return questRes;
    }
}
