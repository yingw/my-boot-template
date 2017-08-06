package cn.yinguowei.boot.repository;

import cn.yinguowei.boot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by yingu on 2017/7/18.
 */
//@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByUsernameLikeOrFullnameLike(String username, String fullname);
    User findByUsername(String username);
    Optional<User> findOneByUsername(String username);
//
//    Future<User> getAllBy();
//    CompletableFuture<User> findById();
}
