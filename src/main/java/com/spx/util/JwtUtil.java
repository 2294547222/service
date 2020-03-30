package com.spx.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    //设置过期时间15分钟
    private static final long EXRIRE_TIME=60 * 60 * 1000;
    //token私钥
    private static final String TOKEN_SECRET="05cc19ab2fee4ba78ab3a9c7d04fd68b";
    public static String creatToken(String username,String userId,String nickname) {
        try {
            //过期时间
            Date date=new Date(System.currentTimeMillis()+EXRIRE_TIME);
            //私钥及加密算法
            Algorithm algorithm=Algorithm.HMAC256(TOKEN_SECRET);
            Map<String,Object> header=new HashMap<>(2);
            header.put("type","JWT");
            header.put("alg","HS256");
            return JWT.create()
                    .withHeader(header)
                    .withClaim("nickname",nickname)
                    .withClaim("loginName",username)
                    .withClaim("userId",userId)
                    .withExpiresAt(date)
                    .sign(algorithm);
        }catch (UnsupportedEncodingException e){
            return null;
        }

    }
    public static String creatToken(String username,String id) {
        try {
            //过期时间
            Date date=new Date(System.currentTimeMillis()+EXRIRE_TIME);
            //私钥及加密算法
            Algorithm algorithm=Algorithm.HMAC256(TOKEN_SECRET);
            Map<String,Object> header=new HashMap<>(2);
            header.put("type","JWT");
            header.put("alg","HS256");
            return JWT.create()
                    .withHeader(header)
                    .withClaim("loginName",username)
                    .withClaim("userId",id)
                    .withExpiresAt(date)
                    .sign(algorithm);
        }catch (UnsupportedEncodingException e){
            return null;
        }

    }
    //token解码
    public static boolean verify(String token) {
        try{
            Algorithm algorithm=Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier=JWT.require(algorithm).build();
            DecodedJWT jwt=verifier.verify(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    //获取用户名
    public static String getName(String token){
        try{
            DecodedJWT jwt=JWT.decode(token);
            return jwt.getClaim("loginName").asString();
        }catch (JWTDecodeException e){
            return null;
        }
    }
    //获取用户Id
    public static String getID(String token){
        try{
            DecodedJWT jwt=JWT.decode(token);
            return jwt.getClaim("userId").asString();
        }catch (JWTDecodeException e){
            return null;
        }
    }
    //获取用户昵称
    public static String getNickname(String token){
        try{
            DecodedJWT jwt=JWT.decode(token);
            return jwt.getClaim("nickname").asString();
        }catch (JWTDecodeException e){
            return null;
        }
    }
}
