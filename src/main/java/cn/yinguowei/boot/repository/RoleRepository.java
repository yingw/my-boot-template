package cn.yinguowei.boot.repository;

import cn.yinguowei.boot.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

/**
 * Created by yingu on 2017/7/23.
 */
@RepositoryRestResource
public interface RoleRepository extends JpaRepository<Role, Long> {
    public Role findByName(String name);
    Role findOneByName(String name);
}
