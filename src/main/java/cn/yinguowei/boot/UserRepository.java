package cn.yinguowei.boot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by yingu on 2017/7/18.
 */
//@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {

//    List<User> findByUsernameLikeOrFullnameLike(String username, String fullname);
}
