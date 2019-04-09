package cn.foobar.forum.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Author User
 * @Date 2019/4/6 9:11
 * @Version 1.0.0
 * @Description
 **/
@Entity
@Table(name = "users")
@ToString(callSuper = true)
@NoArgsConstructor
@Data
@AllArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name", length = 30, unique = true, nullable = false)
    @NotBlank
    private String userName;

    @Column(name = "user_pass", nullable = false)
    @NotBlank
    private String userPass;

    @Column(name = "user_email", nullable = false)
    @NotNull
    private String userEmail;

    /**
     * 用户注册日期
     */
    @Column(name = "user_date", updatable = false, nullable = false)
    @CreationTimestamp
    private Date userDate;

    /**
     * 用户类型
     */
    @Enumerated
    @Column(nullable = false)
    private UserState userLevel;
}
