package com.sim.emoji.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    private Long id;
    private String userId;
    private String userPw;
    private String userName;
    private String userNickname;
    private String userLink;

}
