package com.api.trendiez.Security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthHandler {

    private static final Logger logger = LoggerFactory.getLogger(AuthHandler.class);
    public static String verifyAuthToken(String bearerToken){
        String token = extractJwtFromRequest(bearerToken);
        if(token == null){
            logger.info("The bearer token is null");
            return null;
        }

        String uid = decryptToken(token);

        if(uid == null){
            logger.info("The uid is null");
            return null;
        }

        return uid;
    }


    public static  String decryptToken(String token){

        // decrypt the jwt token
        
        return null;
    }

    private static String extractJwtFromRequest(String bearerToken) {
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        logger.info("Not Auth bearer token has been supplied");
        return null;
    }
}


