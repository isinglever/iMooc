package i.mooc.service.center;

import i.mooc.pojo.Users;
import i.mooc.pojo.bo.UserBO;

public interface CenterUserService {
    public Users queryUserInfo(String userId);
}
