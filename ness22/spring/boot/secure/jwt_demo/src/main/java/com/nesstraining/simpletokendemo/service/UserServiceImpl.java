package com.nesstraining.simpletokendemo.service;

import com.nesstraining.simpletokendemo.dto.LoginRequest;
import com.nesstraining.simpletokendemo.dto.RegisterRequest;
import com.nesstraining.simpletokendemo.dto.UserDetails;
import com.nesstraining.simpletokendemo.entity.User;
import com.nesstraining.simpletokendemo.exceptions.AppUserNotFoundException;
import com.nesstraining.simpletokendemo.exceptions.IncorrectCredentialsException;
import com.nesstraining.simpletokendemo.repo.IUserRepository;
import com.nesstraining.simpletokendemo.util.JwtTokenUtil;
import com.nesstraining.simpletokendemo.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository repo;

    @Autowired
    private UserUtil userUtil;

    @Autowired
    private JwtTokenUtil tokenUtil;

    @Override
    public UserDetails register(RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user=repo.save(user);
        return userUtil.toDetails(user);
    }


    User findByUsername(String username) throws AppUserNotFoundException {
        Optional<User> optional = repo.findUserByUsername(username);
        if (!optional.isPresent()) {
            throw new AppUserNotFoundException("user not found for username=" + username);
        }
        return optional.get();
    }


    @Override
    public String login(LoginRequest request) throws AppUserNotFoundException, IncorrectCredentialsException {
        User user = findByUsername(request.getUsername());
        if (!user.getPassword().equals(request.getPassword())) {
            throw new IncorrectCredentialsException("incorrect credentials");
        }
        return tokenUtil.generateToken(request.getUsername());

    }

    @Override
    public UserDetails authenticateByToken(String token) throws AppUserNotFoundException{
       String  username= tokenUtil.decodeToken(token);
       User user= findByUsername(username);
        return userUtil.toDetails(user);
    }

    @Override
    public List<UserDetails>findAll(){
        List<User>users= repo.findAll();
        return userUtil.toDetailsList(users);
    }

}
