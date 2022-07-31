package com.plogging.domain.Quest.dto.quest.request;

import com.plogging.domain.Quest.entity.Quest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuestReq {

    private String name;
    private MultipartFile photo;

    public Quest toEntity(String photo) {
        return new Quest(this.name, photo);
    }
}
