package com.testeTHMAPI.config;

import com.testeTHMAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;


//TODO *** OBS: ler o arquivo "Recaptulando.txt" para mais informações sobre os comentário enumerados ***

@Configuration //marca como uma classe de configuração do Spring
@EnableWebSecurity //ativa a segurança
public class SecurityConfig extends WebSecurityConfigurerAdapter { // 1


    @Lazy //2- só executa ao ser chamado
    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Bean
    public OncePerRequestFilter jwtFilter(){
        return new JwtAuthFilter(jwtService, userService);
    }

    //3-chamei a função responsável por criptografar a senha
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();

    }

    //4- função responsável por chamar os métodos de autenticação
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService)
                .passwordEncoder(passwordEncoder());
    }

    //5- parte de autorizações para determinados caminhos
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .antMatchers("/user/list/**")
                .hasRole("ADMIN")
                .antMatchers("/ocorrencias/list/**")
                .permitAll()
                .antMatchers("/user/alterar/{id}/**")
                .hasAnyRole("USER", "ADMIN")
                .antMatchers("/user/deletar/{id}/**")
                .hasRole("ADMIN")
                .antMatchers("/ocorrencias/deletar/{id}/**")
                .authenticated()
                .antMatchers("/ocorrencias/cadastro/**")
                .hasAnyRole("USER", "ADMIN")
                .antMatchers("/user/cadastro/**")
                .permitAll()
                .antMatchers("/user/auth/**")
                .permitAll()
                .antMatchers("/user/{id}/**")
                .authenticated()
                .and()
                //.httpBasic();
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}



