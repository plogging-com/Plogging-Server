package com.plogging.domain.Quest.dto.userQuestDiary.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuestDiaryReq{

    @NotNull
    private String comment;

    private List<MultipartFile> photos;
}
