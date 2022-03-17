package i.mooc.service.impl;

import i.mooc.mapper.StuMapper;
import i.mooc.pojo.Stu;
import i.mooc.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StuServiceImpl implements StuService {
    
    @Autowired
    private StuMapper stuMapper;
    
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Stu getStuInfo(int id) {
        return stuMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveStu() {
        Stu stu = new Stu();
        stu.setAge(35);
        stu.setName("pan");
        
        stuMapper.insert(stu);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateStu(int id) {
        Stu stu = new Stu();
        stu.setId(id);
        stu.setAge(25);
        stu.setName("jack");
        stuMapper.updateByPrimaryKey(stu);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteStu(int id) {
        stuMapper.deleteByPrimaryKey(id);
    }   
    
    public void saveParent() {
        Stu stu = new Stu();
        stu.setName("parent");
        stu.setAge(19);
        stuMapper.insert(stu);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveChildren() {
        saveChild1();
//        int a = 1 / 0;
        saveChild2();
    }

    public void saveChild1() {
        Stu stu1 = new Stu();
        stu1.setName("child-1");
        stu1.setAge(11);
        stuMapper.insert(stu1);
    }
    public void saveChild2() {
        Stu stu2 = new Stu();
        stu2.setName("child-2");
        stu2.setAge(22);
        stuMapper.insert(stu2);
    }
}
