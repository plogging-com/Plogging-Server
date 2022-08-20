package com.plogging.domain.Quest.dto.userQuestDiary.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuestDiaryReq {
    private String comment;
    private MultipartFile photo;
}
