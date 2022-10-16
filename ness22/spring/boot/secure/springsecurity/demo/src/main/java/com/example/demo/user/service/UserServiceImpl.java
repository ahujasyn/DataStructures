package com.example.demo.user.service;

import com.example.demo.JwtTokenUtil;
import com.example.demo.user.dto.CustomUserDetails;
import com.example.demo.user.entity.User;
import com.example.demo.user.exceptions.IncorrectCredentialsException;
import com.example.demo.user.repo.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class UserServiceImpl implements UserDetailsService,IUserService {
    @Autowired
    private IUserRepository userRepo;

    @Autowired
    private JwtTokenUtil tokenUtil;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("inside loaduserbysername");
        User user=findByUsername(username);
      CustomUserDetails desired=new CustomUserDetails(user);
      return desired;
    }

    User findByUsername(String username){
        Optional<User>optional= userRepo.findByUsername(username);
        if(!optional.isPresent()){
            throw new UsernameNotFoundException("user with "+username+" not found");

        }
         return optional.get();
    }

    @Override
    public String login(String username, String password) throws IncorrectCredentialsException{
        User user=findByUsername(username);
        if(!user.getUsername().equalsIgnoreCase(username)|| ! user.getPassword().equals(password)){
            throw new IncorrectCredentialsException("incorrect credentils");
        }
        return tokenUtil.generateToken(username);
    }

}
