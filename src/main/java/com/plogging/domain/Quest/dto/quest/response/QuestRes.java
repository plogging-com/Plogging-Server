package com.plogging.domain.Quest.dto.quest.response;

import com.plogging.domain.Quest.entity.Quest;
import com.plogging.global.utill.imgae.AwsS3Service;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuestRes{

    private String name;
    private String photo;
    private int maxLevel;

    public static QuestRes create(Quest quest){
        QuestRes questRes = new QuestRes();
        questRes.name = quest.getName();
        questRes.maxLevel = quest.getMaxLevel();
        questRes.photo = AwsS3Service.makeUrlOfFilename(quest.getPhoto());
        return questRes;
    }
}
