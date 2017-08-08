package cn.yinguowei.boot.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * 创建 by 殷国伟 于 2017/8/8.
 */
@Entity
@Getter
@ToString
@NoArgsConstructor
@Immutable
@Table(name = "persistent_logins")
public class UserToken {
    @Id
    protected String series;
    protected String username;
    protected String token;
    protected LocalDateTime lastUsed;
}
