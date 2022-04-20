package i.mooc.service.impl.center;

import i.mooc.mapper.UsersMapper;
import i.mooc.pojo.Users;
import i.mooc.service.center.CenterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CenterUserServiceImpl implements CenterUserService {
    
    @Autowired
    public UsersMapper usersMapper;
    
    
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Users queryUserInfo(String userId) {
        Users user =  usersMapper.selectByPrimaryKey(userId);
        user.setPassword(null);
        
        return user;
    }
}
