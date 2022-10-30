package top.devildyw.satokenlearn.satoken_common.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @author Devil
 * @since 2022-10-30-15:49
 */
@Data
@TableName(value = "user",autoResultMap = true)
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String username;

    private String password;

    private String mail;

    private String name;

    private String salt;

    private Integer age;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
