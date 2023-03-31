package com.testeTHMAPI.config;

import com.testeTHMAPI.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtService {

    @Value("${security.jwt.expiracao}")
    private String expiracao;

    @Value("{security.jwt.chave-assinatura}")
    private String chaveAssinatura;

    public String gerarToken(User user){

        Long expString = Long.valueOf(expiracao);

        LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(expString);

        Instant instant  = dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant();
        Date data = Date.from(instant);

//        HashMap<String, Object> claims = new HashMap<>();
//
//        claims.put("role do user", user.isAdmin());

        return Jwts
                .builder()
                //.setId(String.valueOf(user.getId()))
                .setIssuer(user.getNome())
                .setSubject(user.getLogin())
                //.setClaims(claims)
                .setExpiration(data)
                .signWith( SignatureAlgorithm.HS512, chaveAssinatura )
                .compact();
    }

    private Claims obterClains(String token) throws ExpiredJwtException {
        return Jwts
                .parser()
                .setSigningKey(chaveAssinatura)
                .parseClaimsJws(token)
                .getBody(); //retorna os claims do token
    }

    public boolean tokenValido( String token){
        try{
            Claims claims = obterClains(token);
            Date dataExpiracao = claims.getExpiration();
            LocalDateTime data = dataExpiracao.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

            return !LocalDateTime.now().isAfter(data);

        }catch (Exception e){
            return false;
        }
    }

    public String obterLoginUser (String token) throws  ExpiredJwtException{
        return(String) obterClains(token).getSubject();
    }

//    public static void main(String[] args){
//        ConfigurableApplicationContext context = SpringApplication.run(TesteThmApiApplication.class);
//        JwtService service = context.getBean(JwtService.class);
//            User user = User.builder()
//                    .nome("almir")
//                    .email("ff")
//                    .id(7)
//                    .build();
//            String token = service.gerarToken(user);
//            System.out.println(token);
//
//            boolean isTokenValido = service.tokenValido(token);
//        System.out.println("Valido?: "+isTokenValido);
//
//        System.out.println(service.obterLoginUser(token));
//
//    }

}
