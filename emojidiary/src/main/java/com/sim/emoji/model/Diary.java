package com.sim.emoji.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Diary {
    private Long id;
    private Long diaryWriter;
    private String diaryTitle;
    private String diaryContent;
    private String diaryImage;
    private LocalDateTime diaryDate;
    private String emojiMood;
    private String emojiWeather;
    private String emojiPeople;
}
