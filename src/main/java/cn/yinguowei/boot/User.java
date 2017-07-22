package cn.yinguowei.boot;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * Created by yingu on 2017/7/18.
 */
@Entity
@Data//LOMBOK
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue
    private Long id;

    /**
     * 账户名
     */
    @NotNull
    @Column(unique = true)
    @NonNull
    private String username;

    /**
     * 姓氏
     */
    @NotNull
    @NonNull
    private String fullname;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 逻辑删除状态 0 未删除 1 删除
     */
    private Boolean status;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime created;

    @LastModifiedDate
    private LocalDateTime updated;

    public boolean isNew() {
        return this.id == null;
    }

}
