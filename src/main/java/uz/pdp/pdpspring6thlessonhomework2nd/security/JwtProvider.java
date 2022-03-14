package uz.pdp.pdpspring6thlessonhomework2nd.security;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import uz.pdp.pdpspring6thlessonhomework2nd.entity.Role;


import java.util.Date;
import java.util.Set;

@Component
public class JwtProvider {
    private static final long expiredTime = 1000*60*60*24;
    private static final  String secretKey = "PDPuzSpringLessons";

    public String generateToken(String username, Set<Role> role){
       Date expireDate =  new Date(System.currentTimeMillis()+expiredTime);
        String token = Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .claim("roles", role)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
        return token;

    }
////    public boolean validateToken(String token){
////        try {
////
////
////            Jwts
////                    .parser()
////                    .setSigningKey(secretKey)
////                    .parseClaimsJwt(token);
////            return true;
////        }catch (Exception e){
////            e.printStackTrace();
////        }
////        return false;
////    }
    public String getUsernameFromToken(String token){
        try {


            String email = Jwts
                    .parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJwt(token)
                    .getBody()
                    .getSubject();
            return email;
        }catch (Exception e){
            return  null;
        }
    }
//    public Object getRoleFromToken(String token){
//        try {
//            Object roles = Jwts
//                    .parser()
//                    .setSigningKey(secretKey)
//                    .parseClaimsJwt(token)
//                    .getBody()
//                    .get("roles");
//            return roles;
//        }catch (Exception e){
//            return null;
//        }
//    }
}
