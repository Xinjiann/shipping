package com.blog.common.dto;

import java.io.Serializable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SignUpDto implements Serializable {
  @NotNull(message = "昵称不能为空")
  private String username;

  @NotNull(message = "密码不能为空")
  private String password;

  @NotNull(message = "邮箱不能为空")
  @Email(message = "邮箱格式不正确")
  private String email;
}
