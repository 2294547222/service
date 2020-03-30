package com.spx.service;

import com.spx.dao.UserinfoMapper;
import com.spx.entity.Userinfo;
import com.spx.entity.UserinfoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserinfoService {
    @Autowired
    private UserinfoMapper userinfoMapper;
    public long insertUsers(String username, String password){
        Userinfo userinfo=new Userinfo();
        userinfo.setUsername(username);
        userinfo.setPassword(password);
        int count=userinfoMapper.insert(userinfo);
        return count;
    }
    public  long selcetUsername(String username){
        UserinfoExample example=new UserinfoExample();
        UserinfoExample.Criteria criteria=example.createCriteria();
          criteria.andUsernameEqualTo(username);
          long count=userinfoMapper.countByExample(example);
          return count;
    }

    public List login(String username){
         UserinfoExample example=new UserinfoExample();
         UserinfoExample.Criteria criteria=example.createCriteria();
         criteria.andUsernameEqualTo(username);
         List count=userinfoMapper.selectByExample(example);
         if(count.size()==0){
         return null;
         }else {
         return count;
        }
    }
}
