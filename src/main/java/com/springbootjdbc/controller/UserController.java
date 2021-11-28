package com.springbootjdbc.controller;

import com.springbootjdbc.model.User;
import com.springbootjdbc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 根据ID查询用户
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "user/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable(value = "id") Integer id) {
        User user = new User();
        try {
            user = userService.getUserById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * 查询用户列表
     *
     * @return
     */
    @RequestMapping(value = "users", method = RequestMethod.GET)
    public List<User> getUserList() {
        List<User> users = new ArrayList<>();
        try {
            users = userService.getUserList();

        } catch (Exception e) {

            e.printStackTrace();
        }
        return users;
    }

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "user", method = RequestMethod.POST)
    public String add(@RequestBody User user) {

        int orderId = 0;
        try {
            //添加操作返回的是受影响的行数
            orderId = userService.add(user);
            if (orderId == 0) {
                //添加失败
                return "添加失败";
            } else {
                return "添加成功";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "添加失败";
        }

    }

    /**
     * 根据id删除用户
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "user/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable(value = "id") Integer id) {

        try {
            int ret = 0;
            ret = userService.delete(id);
            if (ret <= 0) {
                return "删除失败";
            } else {
                return "删除成功";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "删除失败";
        }
    }

    /**
     * 根据id修改用户信息
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "user/{id}", method = RequestMethod.PUT)
    public String update(@PathVariable("id") Integer id, @RequestBody User user) {
        try {
            int ret = 0;
            ret = userService.update(id, user);
            if (ret <= 0) {
                return "修改失败";
            } else {
                return "修改成功";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "修改失败";
        }
    }

}