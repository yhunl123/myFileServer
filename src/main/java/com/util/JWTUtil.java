package com.util;

import io.jsonwebtoken.*;

import java.util.Date;
import java.util.Map;

public class JWTUtil {

    private static String secretKey = "qudtlsemf";

    private Long valiTime = 1000L * 60 * 60 * 24 * 3;

    public String createToken(Map params) {
        Claims claims = Jwts.claims().setId(secretKey);

        claims.put("memberId", params.get("memberId").toString());
        claims.put("memberName", params.get("memberName").toString());
        claims.put("memberAuth", params.get("memberAuth").toString());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + valiTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public static Jws<Claims> getClaims(String jwt) {
        try {
            // 암호화 키로 복호화한다.
            // 즉 암호화 키가 다르면 에러가 발생한다.
            return Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(jwt);
        }catch(SignatureException e) {
            return null;
        }
    }
    // 토큰 검증 함수
    public boolean validateToken(Jws<Claims> claims) {
        // 토큰 만료 시간이 현재 시간을 지났는지 검증
        return !claims.getBody()
                .getExpiration()
                .before(new Date());
    }
    // 토큰을 통해 Payload의 ID를 취득
    public String getKey(Jws<Claims> claims) {
        // Id 취득
        return claims.getBody()
                .getId();
    }
    // 토큰을 통해 Payload의 데이터를 취득
    public static Object getClaims(Jws<Claims> claims, String key) {
        // 데이터 취득
        return claims.getBody()
                .get(key);
    }

}
