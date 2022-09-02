package com.plogging.domain.Quest.dto.userQuestDiary.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuestDiaryReq{
    private String comment;
    private List<MultipartFile> photos; //TODO 3개 받기
}
