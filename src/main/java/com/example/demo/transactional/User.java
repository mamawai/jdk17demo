package com.example.demo.transactional;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@TableName("user")
@Getter
@Setter
@ToString
public class User {

    public User(String name, String age) {
        this.name = name;
        this.age = age;
    }

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("name")
    private String name;

    @TableField("age")
    private String age;
}
