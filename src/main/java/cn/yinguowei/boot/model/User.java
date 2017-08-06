package cn.yinguowei.boot.model;

import cn.yinguowei.boot.model.base.AbstractAuditingEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by yingu on 2017/7/18.
 */
@Entity
@Data//LOMBOK
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@EntityListeners(AuditingEntityListener.class)
public class User extends AbstractAuditingEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 账户名
     */
    @NotEmpty
    @Column(unique = true)
    @NonNull
    private String username;

    /**
     * 姓名
     */
    @NotEmpty
    @NonNull
    private String fullname;

    /**
     * 用户密码
     */
    @JsonIgnore
    private String password;

    /**
     * 逻辑删除状态 0 未删除 1 删除
     */
    private Boolean active;

//    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

//    @CreatedDate
//    @Column(updatable = false)
//    private LocalDateTime created;
//
//    @LastModifiedDate
//    private LocalDateTime updated;

    public boolean isNew() {
        return this.id == null;
    }

}
