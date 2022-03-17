package i.mooc.controller;

import i.mooc.pojo.bo.UserBO;
import i.mooc.service.StuService;
import i.mooc.service.UserService;
import i.mooc.utils.IMOOCJSONResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    
    @PostMapping("/regist")
    public IMOOCJSONResult regist(@RequestBody UserBO userBO) {
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
        
        userService.creatUser(userBO);
        return IMOOCJSONResult.ok();
    }
}
