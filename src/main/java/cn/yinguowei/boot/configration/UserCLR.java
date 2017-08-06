package cn.yinguowei.boot.configration;

import cn.yinguowei.boot.model.User;
import cn.yinguowei.boot.repository.UserRepository;
import cn.yinguowei.boot.model.Role;
import cn.yinguowei.boot.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

/**
 * Created by yingu on 2017/7/16.
 * 测试数据
 */
@Component
class UserCLR implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;


    @Override
    public void run(String... strings) throws Exception {
        Stream.of("yinguowei,殷国伟", "aimee,夏琴艳", "jon,阮忠友", "james,梁健", "will,Lin Will",
                "yinguowei1,殷国伟", "aimee1,夏琴艳", "jon1,阮忠友", "james1,梁健", "will1,Lin Will",
                "yinguowei2,殷国伟", "aimee2,夏琴艳", "jon2,阮忠友", "james2,梁健", "will2,Lin Will",
                "yinguowei3,殷国伟", "aimee3,夏琴艳", "jon3,阮忠友", "james3,梁健", "will3,Lin Will",
                "yinguowei4,殷国伟", "aimee4,夏琴艳", "jon4,阮忠友", "james4,梁健", "will4,Lin Will",
                "yinguowei5,殷国伟", "aimee5,夏琴艳", "jon5,阮忠友", "james5,梁健", "will5,Lin Will",
                "yinguowei6,殷国伟", "aimee6,夏琴艳", "jon6,阮忠友", "james6,梁健", "will6,Lin Will",
                "yinguowei7,殷国伟", "aimee7,夏琴艳", "jon7,阮忠友", "james7,梁健", "will7,Lin Will",
                "yinguowei8,殷国伟", "aimee8,夏琴艳", "jon8,阮忠友", "james8,梁健", "will8,Lin Will",
                "yinguowei9,殷国伟", "aimee9,夏琴艳", "jon9,阮忠友", "james9,梁健", "will9,Lin Will"
        )
                .forEach(name -> userRepository.save(new User(name.split(",")[0], name.split(",")[1])));
        userRepository.findAll().forEach(u -> {
            System.out.println(u);
            u.setPassword("d8d8a6cd1ed4902505882b6c901812b2");
            userRepository.save(u);
        });

        /* Role */
        Stream.of("admin", "user").forEach(name -> roleRepository.save(new Role(name)));
        roleRepository.findAll().forEach(System.out::println);

        User yinguowei = userRepository.findByUsername("yinguowei");
        yinguowei.getRoles().add(roleRepository.findByName("admin"));
        userRepository.save(yinguowei);
    }
}
