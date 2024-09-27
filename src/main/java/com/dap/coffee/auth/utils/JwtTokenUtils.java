package com.dap.coffee.auth.utils;

import com.dap.coffee.auth.model.CustomUserDetails;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.Serial;
import java.security.SecureRandom;
import java.util.*;
import java.util.function.Function;

@Component
@Slf4j
public class JwtTokenUtils {
    public static final long JWT_TOKEN_VALIDITY = 24 * 60 * 60 * 1000L;
    public static final long REFRESH_TOKEN_VALIDITY = 7 * 24 * 60 * 60 * 1000L;
    @Serial
    private static final long serialVersionUID = -2550185165626007488L;
    private static final String USERNAME = "userName";
    private static final String USER_ID = "userId";
    private static final String AUTHORITIES = "authorities";

    @Value("${security.jwt.secret}")
    private String secret;
    @Value("${security.jwt.secret.refresh}")
    private String secretRefresh;

    public static String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        } else {
            log.warn("JWT Token does not begin with Bearer String");
        }
        return null;
    }

    public String getUsernameFromRequest(HttpServletRequest request) {
        String token = getJwtFromRequest(request);
        return getUsernameFromToken(token);
    }

    public List<String> getRolesFromRequest(HttpServletRequest request) {
        String token = getJwtFromRequest(request);
        return getAuthoritiesFromToken(token);
    }

    public Claims getAllClaimsFromToken(String token, String secret)
            throws MalformedJwtException, ExpiredJwtException,
            UnsupportedJwtException, IllegalArgumentException {

        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

    }

    public String generateToken(CustomUserDetails userDetails) {
        Map<String, Object> claims = createClaimsInfo(userDetails);
        return doGenerateToken(claims);
    }

    public String generateToken(String refreshToken) {
        Claims claims = getAllClaimsFromToken(refreshToken, secretRefresh);
        return doGenerateToken(claims);
    }

    public String generateRefreshToken(CustomUserDetails userDetails) {
        Map<String, Object> claims = createClaimsInfo(userDetails);
        return doGenerateRefreshToken(claims);
    }

    public Map<String, Object> createClaimsInfo(CustomUserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(USERNAME, userDetails.getUsername());
        claims.put(USER_ID, userDetails.getId());
        List<String> authorities = new ArrayList<>();
        userDetails.getAuthorities().forEach(c -> authorities.add(c.getAuthority()));
        claims.put(AUTHORITIES, authorities);
        return claims;
    }

    private String doGenerateToken(Map<String, Object> claims) {

        return Jwts.builder().setClaims(claims).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    private String doGenerateRefreshToken(Map<String, Object> claims) {
        return Jwts.builder().setClaims(claims).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS512, secretRefresh).compact();
    }

    public boolean validateRefreshToken(String refreshToken) {
        try {
            getAllClaimsFromToken(refreshToken, secretRefresh);
            return true;
        } catch (MalformedJwtException ex) {
            log.error("Invalid Refresh token.");
        } catch (ExpiredJwtException ex) {
            log.error("Expired Refresh token.");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported Refresh token.");
        } catch (IllegalArgumentException ex) {
            log.error("Refresh token claims string is empty.");
        }
        return false;
    }

    private String generatingRandomAlphanumericString(int targetStringLength) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        SecureRandom random = new SecureRandom();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public String generateHandshakeToken() {
        return generatingRandomAlphanumericString(10);
    }


    //retrieve username from jwt token
    public String getUsernameFromToken(String token) {
        final Claims claims = getAllClaimsFromToken(token);
        return claims.get(USERNAME).toString();
    }

    public String getUserIdFromToken(String token) {
        final Claims claims = getAllClaimsFromToken(token);
        return claims.get(USER_ID).toString();
    }

    public List<String> getAuthoritiesFromToken(String token) {
        final Claims claims = getAllClaimsFromToken(token);
        return (List<String>) claims.get(AUTHORITIES);
    }

    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    //for retrieve any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) throws MalformedJwtException,
            ExpiredJwtException, UnsupportedJwtException, IllegalArgumentException {
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token.");
            throw ex;
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token.");
            throw ex;
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token.");
            throw ex;
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
            throw ex;
        }
    }

    //check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //validate token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public Boolean validateToken(String token) {
        final String username = getUsernameFromToken(token);
        return (!StringUtils.isEmpty(username) && !isTokenExpired(token));
    }

}
