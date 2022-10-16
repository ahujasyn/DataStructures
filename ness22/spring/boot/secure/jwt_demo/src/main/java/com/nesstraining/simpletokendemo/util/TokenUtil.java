package com.nesstraining.simpletokendemo.util;

import org.springframework.stereotype.Component;

//@Component
public class TokenUtil {

    public String generateToken(String username) {
        long currentTime = System.currentTimeMillis();
        String token = username + "-" + currentTime;
        return token;
    }

    public TokenPayload decodeToken(String token) {
        String parts[] = token.split("-");
        TokenPayload payload = new TokenPayload();
        payload.username = parts[0];
        long created = Long.parseLong(parts[1]);
        payload.createdTimeMilli = created;
        return payload;
    }


    public static class TokenPayload {
        public String username;
        public long createdTimeMilli;

    }


}
