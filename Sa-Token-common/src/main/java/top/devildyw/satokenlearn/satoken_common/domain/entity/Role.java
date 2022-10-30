package top.devildyw.satokenlearn.satoken_common.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @author Devil
 * @since 2022-10-30-15:52
 */
@Data
@TableName(value = "role",autoResultMap = true)
public class Role {
    @TableId(type = IdType.AUTO)
    private Integer rId;

    private String rName;

    private String rState;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
