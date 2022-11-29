package com.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JWTUtil {

    private String secretKey = "qudtlsemf";

    private Long valiTime = 1000L * 60 * 60 * 24 * 3;

    public String createToken(Map params) {
        Claims claims = Jwts.claims().setId(secretKey);

        claims.put("memberName", params.get("memberName").toString());
        claims.put("memberAuth", params.get("memberAuth").toString());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + valiTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

}
