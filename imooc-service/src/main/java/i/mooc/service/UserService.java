package i.mooc.service;

import i.mooc.pojo.Stu;
import i.mooc.pojo.Users;
import i.mooc.pojo.bo.UserBO;

public interface UserService {
    public boolean queryUsernameIsExist(String username);
    public Users creatUser(UserBO userBO);
}
