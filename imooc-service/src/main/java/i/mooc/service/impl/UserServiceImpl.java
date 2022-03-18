package i.mooc.service.impl;

import i.mooc.enums.Sex;
import i.mooc.mapper.StuMapper;
import i.mooc.mapper.UsersMapper;
import i.mooc.pojo.Stu;
import i.mooc.pojo.Users;
import i.mooc.pojo.bo.UserBO;
import i.mooc.service.StuService;
import i.mooc.service.UserService;
import i.mooc.utils.DateUtil;
import i.mooc.utils.MD5Utils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    
    private static final String DEFAULT_IMG = "https://images.unsplash.com/photo-1633332755192-727a05c4013d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MXx8dXNlcnxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=700&q=60";
    
    @Autowired
    public UsersMapper usersMapper;
    
    @Autowired
    private Sid sid;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryUsernameIsExist(String username) {

        Example userExample = new Example(Users.class);
        Example.Criteria userCriteria = userExample.createCriteria();
        userCriteria.andEqualTo("username", username);
        Users res = usersMapper.selectOneByExample(userExample);
        return res == null ? false : true;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Users creatUser(UserBO userBO) {
        
        String userId = sid.nextShort();
        
        Users user = new Users();
        user.setId(userId);
        user.setUsername(userBO.getUsername());
        try {
            user.setPassword(MD5Utils.getMD5Str(userBO.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        user.setNickname(userBO.getUsername());
        user.setFace(DEFAULT_IMG);
        user.setBirthday(DateUtil.stringToDate("1995-01-01"));
        user.setSex(Sex.secret.type);
        user.setCreatedTime(new Date());
        user.setUpdatedTime(new Date());
        
        usersMapper.insert(user);
        return user;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Users queryUserForLogin(String username, String password) {

        Example userExample = new Example(Users.class);
        Example.Criteria userCriteria = userExample.createCriteria();
        userCriteria.andEqualTo("username", username);
        userCriteria.andEqualTo("password", password);
        
        Users result = usersMapper.selectOneByExample(userExample);
        
        return result;
    }
}
