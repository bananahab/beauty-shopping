package com.beauty.shopping.common.utils;

import com.beauty.shopping.common.api.ResultCode;
import com.beauty.shopping.common.exception.CommonException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * @author wuzhenxian
 * @date 2021/03/15
 */
@Slf4j
public class TokenUtil {



    public static String createJwtToken(String id, String issuer, long ttlMillis, String tokenSecret) {
        return createJwtToken(id, issuer, "", ttlMillis, tokenSecret);
    }

    /**
     * 生成token
     * @param id 编号
     * @param issuer 该JWT的签发者，是否使用时可选的
     * @param subject 该JWT所面对的用户，是否使用是可选的；
     * @param ttlMillis 签发时间
     * @return token
     */
    public static String createJwtToken(String id, String issuer, String subject, long ttlMillis, String tokenSecret) {
        //签名算法，将对token进行签名
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //生成签发时间
        long nowMillis = System.currentTimeMillis();
        Date nowDate = new Date(nowMillis);

        //通过密钥签名JWT
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(tokenSecret);
        String jcaName = signatureAlgorithm.getJcaName();
        Key signKey = new SecretKeySpec(apiKeySecretBytes, jcaName);

        //设置JWT声明
        JwtBuilder jwtBuilder = Jwts.builder().setId(id)
                .setIssuedAt(nowDate)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signKey);

        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);
        jwtBuilder.setExpiration(expDate);
        log.info("token生成：{}", jwtBuilder.compact());
        return jwtBuilder.compact();
    }

    /**
     * token解析方法
     * @param token 签证
     * @return 解析结果
     */
    public static Claims parseJWT(String token, String tokenSecret) {
        Claims claims =  Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(tokenSecret))
                .parseClaimsJws(token).getBody();
        if (claims.getExpiration().before(new Date())) {
            return null;
        }
        return claims;
    }
}
