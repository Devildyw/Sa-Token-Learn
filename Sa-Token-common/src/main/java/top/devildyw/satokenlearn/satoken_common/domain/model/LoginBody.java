package top.devildyw.satokenlearn.satoken_common.domain.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Devil
 * @since 2022-10-30-16:05
 */
@Data
public class LoginBody {
    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "用户名不能为空")
    private String password;

//    private String code;
}
