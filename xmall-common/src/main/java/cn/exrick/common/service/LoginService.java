package cn.exrick.common.service;


import cn.exrick.common.pojo.Member;

public interface LoginService {

    Member userLogin(String username, String password);

    Member getUserByToken(String token);

    int logout(String token);
}
