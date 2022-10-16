package com.nesstraining.simpletokendemo.util;

import com.nesstraining.simpletokendemo.dto.UserDetails;
import com.nesstraining.simpletokendemo.entity.User;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserUtil {

    public UserDetails toDetails(User user) {
        UserDetails desired = new UserDetails();
        desired.setId(user.getId());
        desired.setUsername(user.getUsername());
        desired.setPassword(user.getPassword());
        return desired;
    }

    public List<UserDetails> toDetailsList(Collection<User> users) {
        return users.stream().map(user -> toDetails(user)).collect(Collectors.toList());
    }
}
