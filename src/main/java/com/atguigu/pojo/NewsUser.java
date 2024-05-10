package com.atguigu.pojo;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class NewsUser {
    private Integer uid;
    private String username;
    private String userPwd;
    private String nickName;
}
