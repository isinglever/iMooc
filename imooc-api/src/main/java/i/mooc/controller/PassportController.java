package i.mooc.controller;

import i.mooc.pojo.Users;
import i.mooc.pojo.bo.UserBO;
import i.mooc.service.StuService;
import i.mooc.service.UserService;
import i.mooc.utils.CookieUtils;
import i.mooc.utils.IMOOCJSONResult;
import i.mooc.utils.JsonUtils;
import i.mooc.utils.MD5Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@Controller
@RestController
@RequestMapping("passport")
public class PassportController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/usernameIsExist")
    public IMOOCJSONResult usernameIsExist(@RequestParam String username) {
        if (StringUtils.isBlank(username)) {
            return IMOOCJSONResult.errorMsg("username cannot null");
        }
        
        boolean isExist = userService.queryUsernameIsExist(username);
        if (isExist) return IMOOCJSONResult.errorMsg("the username is exist");
        
        return IMOOCJSONResult.ok();
    }
    
    @PostMapping("/login")
    public IMOOCJSONResult login(@RequestBody UserBO userBO, 
                                 HttpServletRequest request, 
                                 HttpServletResponse response) throws Exception{
        String username = userBO.getUsername();
        String password = userBO.getPassword();
        
        //check username and password whether null
        if (StringUtils.isBlank(username) || 
                StringUtils.isBlank(password)) {
            return IMOOCJSONResult.errorMsg("username or password can't be null");
        }
        
        Users userResult = userService.queryUserForLogin(username, MD5Utils.getMD5Str(password));
        
        if (userResult == null) return IMOOCJSONResult.errorMsg("username or password is incorrect");
        
        userResult.setPassword(null);
        userResult = setNullProperty(userResult);


        CookieUtils.setCookie(request, response, "user",
                JsonUtils.objectToJson(userResult), true);
        return IMOOCJSONResult.ok(userResult);



    // TODO 生成用户token，存入redis会话
    // TODO 同步购物车数据

    }

    private Users setNullProperty(Users userResult) {
        userResult.setPassword(null);
        userResult.setMobile(null);
        userResult.setEmail(null);
        userResult.setCreatedTime(null);
        userResult.setUpdatedTime(null);
        userResult.setBirthday(null);
        return userResult;
    }

    @PostMapping("/regist")
    public IMOOCJSONResult regist(@RequestBody UserBO userBO, 
                                  HttpServletResponse response, 
                                  HttpServletRequest request) {
        String username = userBO.getUsername();
        String password = userBO.getPassword();
        String confirmPassword = userBO.getConfirmPassword();

        //check username and password whether null
        if (StringUtils.isBlank(username) ||
                StringUtils.isBlank(password) ||
                StringUtils.isBlank(confirmPassword)) {
            return IMOOCJSONResult.errorMsg("username or password can't be null");
        }

        boolean isExist = userService.queryUsernameIsExist(username);
        if (isExist) return IMOOCJSONResult.errorMsg("the username is exist");

        if (password.length() < 6) return IMOOCJSONResult.errorMsg("password length shouldn't less than six");

        if (!password.equals(confirmPassword)) return IMOOCJSONResult.errorMsg("password does not match");

        Users userResult = userService.creatUser(userBO);
        CookieUtils.setCookie(request, response, "user",
                JsonUtils.objectToJson(userResult), true);
        
        return IMOOCJSONResult.ok();
    }
    
    @PostMapping("/logout")
    public IMOOCJSONResult logout(@RequestParam String userId, 
                                  HttpServletResponse response, 
                                  HttpServletRequest request) {
        
        CookieUtils.deleteCookie(request, response, "user");
        return IMOOCJSONResult.ok();
    }
}
