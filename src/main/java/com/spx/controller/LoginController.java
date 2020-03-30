package com.spx.controller;

import com.spx.entity.Userinfo;
import com.spx.result.Result;
import com.spx.service.UserinfoService;
import com.spx.util.JwtUtil;
import com.sun.javafx.collections.MappingChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/register")
public class LoginController {
    @Autowired
    private UserinfoService userinfoService;

    @RequestMapping("/insertUserRegister")
    @ResponseBody
    public Map<String, Object> insertUserRegister(@RequestParam("username") String username,
                                                  @RequestParam("password") String password) {
        Map<String, Object> map = new HashMap<String, Object>();
        long a = userinfoService.selcetUsername(username);
        if (a > 0) {
            map.put("code", 0);
            map.put("massage", "注册失败");
        } else {
            long count = userinfoService.insertUsers(username, password);
            map.put("count", count);
            map.put("code", 1);
            map.put("massage", "注册成功");
        }
        return map;
    }

    @RequestMapping("/login")
    @ResponseBody
    public Result login(@RequestParam("username") String username) {
        List<Userinfo> count = userinfoService.login(username);
        Result result = new Result();
        if (count == null) {
            return Result.err("用户名或密码错误，请重新输入");
        } else {
            String name=count.get(0).getUsername();
            String id=count.get(0).getId()+"";
            String token= JwtUtil.creatToken(name,id);
            result.setUsername(username);
            result.setToken(token);
            result.setCode(1);
        }
return result;
    }


}