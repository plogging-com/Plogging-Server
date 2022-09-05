package com.plogging.domain.Quest.dto.quest.request;

import com.plogging.domain.Quest.entity.Quest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EditQuestReq {
    @NotNull
    private String name;

    private MultipartFile photo;

    @NotNull
    private int maxLevel;
}
