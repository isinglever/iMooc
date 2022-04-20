package i.mooc.controller.center;

import i.mooc.pojo.Users;
import i.mooc.service.center.CenterUserService;
import i.mooc.utils.IMOOCJSONResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "center controller")
@RestController
@RequestMapping("center")
public class CenterController {
    
    @Autowired
    private CenterUserService centerUserService;
    
    @GetMapping("userInfo")
    public IMOOCJSONResult userInfo(@RequestParam String userId) {
        Users user = centerUserService.queryUserInfo(userId);
        return IMOOCJSONResult.ok(user);
    }
}
