package cn.yinguowei.boot.web;

import cn.yinguowei.boot.model.User;
import cn.yinguowei.boot.repository.RoleRepository;
import cn.yinguowei.boot.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
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
@Api(value = "UserController", description = "用户 Web控制器")
@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    UserRepository userRepository;

    RoleRepository roleRepository;

    public UserController(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    private static final String USER_FORM = "users/userForm";
    private static final String USER_LIST = "users/userList";
    private static final String USER_DETAILS = "users/userDetails";

    @ApiOperation(value = "查询所有用户", notes = "返回所有User对象的JSON格式")
    @GetMapping("/users")
    public String queryUsers(
            @ApiParam(name = "name", value = "用户名称查询条件", required = false)
            @RequestParam(defaultValue = "") String name, Model model) {
        // TODO find by name
        List<User> users = userRepository.findByUsernameLikeOrFullnameLike("%" + name + "%", "%" + name + "%");
//        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return USER_LIST;
    }

    @ApiOperation(value = "新增用户初始化", notes = "创建空的User对象返回页面")
    @GetMapping(value = "/users/new")
    public ModelAndView initCreationForm(Model model) {
        return new ModelAndView(USER_FORM, "user", new User());
    }

    @ApiOperation(value = "新增保存", notes = "新增用户，并保存入库")
    @PostMapping("/users/new")
    // @ResponseBody
    public String createUser(
            @ApiParam(name = "用户对象", value = "要保存的用户对象，必要字段：姓名、用户名", required = true)
            @Valid User user, BindingResult result) {
        logger.info("UserController.createUser()： user = [" + user + "], result = [" + result + "]");
        if (result.hasErrors()) {
            return USER_FORM;
        }
//        user.setCreated(LocalDate.now());
        userRepository.save(user);
        return "redirect:/users/" + user.getId();
    }

    @ApiOperation(value = "编辑，初始化")
    @GetMapping(value = "/users/{id}/edit")
    public ModelAndView initEditForm(@PathVariable("id") Long id) {
        User user = userRepository.findOne(id);
//        UserDTO userDto = new UserDTO(user.getId(), user.getUsername(), user.getFullname(), user.getStatus(), new HashSet<>());
//        for (Role role : user.getRoles()) {
//            userDto.getRoles().add(role.getName());
//        }
//        ModelAndView mav = new ModelAndView(USER_FORM, "user", userDto);
        ModelAndView mav = new ModelAndView(USER_FORM, "user", user);
        mav.addObject("allRoles", roleRepository.findAll());
        return mav;
    }

    @ApiOperation(value = "编辑，保存")
    @PutMapping("/users/{id}/edit")
    public String updateUser(@PathVariable("id") Long id, @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            result.getAllErrors().forEach(e -> logger.error(e.toString()));
            return USER_FORM;
        }
        // TODO mapper
        User u = userRepository.findOne(id);
        u.setUsername(user.getUsername());
        u.setFullname(user.getFullname());
        u.setRoles(user.getRoles());
        u.setActive(user.getActive());
        userRepository.save(u);
        /*
        userRepository.findOneByUsername(user.getUsername()).ifPresent(u -> {
            u.setStatus(user.getStatus());
            u.setFullname(user.getFullname());
            u.getRoles().clear();
            u.getRoles().addAll(
                    user.getRoles().stream().map(roleRepository::findOneByName)
                            .collect(Collectors.toSet()));
//            u.setRoles(user.getRoles());
            userRepository.save(u);
        });
*/
//                }
//                user.setId(id);
//        userRepository.save(user);
        return "redirect:/users/{id}";
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        logger.info("deleting user..." + id);
        userRepository.delete(id);
        return "redirect:/users";
    }

    @ApiOperation(value = "详情页面")
    @GetMapping("/users/{id}")
    public ModelAndView getUser(@PathVariable("id") long id) {
        logger.debug("getUser: " + id);
        return new ModelAndView(USER_DETAILS, "user", userRepository.findOne(id));
    }

    @ApiOperation(value = "激活用户")
    @PreAuthorize("hasRole('admin')")
    @PutMapping("/users/{id}/enable")
    @ResponseBody
    public void enableUser(@PathVariable("id") Long id) {
        setUserStatus(id, true);
    }

    @ApiOperation(value = "关闭用户（非删除）")
    @PutMapping("/users/{id}/disable")
    @ResponseBody
    public void disableUser(@PathVariable("id") Long id) {
        setUserStatus(id, false);
    }

    private void setUserStatus(@PathVariable("id") Long id, boolean active) {
        User user = userRepository.findOne(id);
        user.setActive(active);
        userRepository.save(user);
    }

}
