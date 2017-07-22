package cn.yinguowei.boot;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by yingu on 2017/7/18.
 */
@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    private static final String USER_FORM = "users/userForm";

    /**
     * 查询
     *
     * @param name
     * @param model
     * @return
     */
    @ApiOperation(value = "查询所有用户", notes = "返回所有User对象的JSON格式")
    @GetMapping("/users")
    public String queryUsers(
            @ApiParam(name = "name", value = "用户名称查询条件", required = false)
            @RequestParam(defaultValue = "") String name, Model model) {
//        List<User> users = userRepository.findByUsernameLikeOrFullnameLike("%" + name + "%", "%" + name + "%");
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "users/userList";
//        return "index";
    }

    /**
     * 新增用户
     *
     * @param model
     * @return
     */
    @GetMapping(value = "/users/new")
    public ModelAndView initCreationForm(Model model) {
        return new ModelAndView(USER_FORM, "user", new User());
    }

    /**
     * 新增保存
     *
     * @param user
     * @param result
     * @return
     */
    @PostMapping("/users/new")
    // @ResponseBody
    public String createUser(@Valid User user, BindingResult result) {
        System.out.println("user = [" + user + "], result = [" + result + "]");
        logger.info("UserController.createUser()");
        if (result.hasErrors()) {
            return USER_FORM;
        }
//        user.setCreated(LocalDate.now());
        userRepository.save(user);
        return "redirect:/users/" + user.getId();
    }

    /**
     * 编辑，初始化
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/users/edit/{id}")
    public ModelAndView initEditForm(@PathVariable("id") Long id) {
        return new ModelAndView(USER_FORM, "user", userRepository.findOne(id));
    }

    /**
     * 编辑，保存
     *
     * @param id
     * @param user
     * @param result
     * @return
     */
    @PutMapping("/users/edit/{id}")
    public String updateUser(@PathVariable("id") Long id, @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return USER_FORM;
        }
        user.setId(id);
        userRepository.save(user);
        return "redirect:/users/{id}";
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        logger.info("deleting user..." + id);
        userRepository.delete(id);
        return "redirect:/users";
    }

    @RequestMapping("/test")
    public String test() {
        return "404";
    }

    /**
     * 详情
     *
     * @param id
     * @return
     */
    @GetMapping("/users/{id}")
    public ModelAndView getUser(@PathVariable("id") long id) {
        logger.debug("getUser: " + id);
        return new ModelAndView("users/userDetails", "user", userRepository.findOne(id));
    }

    /**
     * 激活用户
     *
     * @param id
     */
    @PutMapping("/users/{id}/enable")
    @ResponseBody
    public void enableUser(@PathVariable("id") Long id) {
        setUserStatus(id, true);
    }

    /**
     * 关闭用户（非删除）
     *
     * @param id
     */
    @PutMapping("/users/{id}/disable")
    @ResponseBody
    public void disableUser(@PathVariable("id") Long id) {
        setUserStatus(id, false);
    }

    private void setUserStatus(@PathVariable("id") Long id, boolean status) {
        User user = userRepository.findOne(id);
        user.setStatus(status);
        userRepository.save(user);
    }

}
