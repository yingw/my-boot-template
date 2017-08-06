package cn.yinguowei.boot.model;

import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by yingu on 2017/7/23.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class Role {
    @Id
    @GeneratedValue
    Long id;

    @NotEmpty
    @NonNull
    String name;
}
