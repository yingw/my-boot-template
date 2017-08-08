package cn.yinguowei.boot.repository;

import cn.yinguowei.boot.model.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 创建 by 殷国伟 于 2017/8/8.
 */
public interface UserTokenRepository extends JpaRepository<UserToken, String> {
    public List<UserToken> findByUsernameLike(String username);
}
