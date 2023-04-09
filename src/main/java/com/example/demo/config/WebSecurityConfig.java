package com.example.demo.config;

import com.example.demo.services.LanofromatiqueUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LanofromatiqueUserDetailsService lanofromatiqueUserDetailsService;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                // ay requete jat f "/" dewzo liha
                .antMatchers("/").permitAll()
                .antMatchers("/creer-compte**").permitAll()
                .antMatchers("/todo**").hasRole("USER_3ADI")
               /* .antMatchers("/admin/supprimer**").hasRole("USER_SUPER_ADMIN")
                .antMatchers("/admin**").hasAnyRole("USER_ADMIN", "USER_SUPER_ADMIN")*/

                // ay rquette akhra f ay URI khas user ykon mconnecte
                .anyRequest().authenticated();

       http.formLogin()
                .loginPage("/se-connecter").permitAll().
                and().formLogin()
                .usernameParameter("k").passwordParameter("l").defaultSuccessUrl("/").failureUrl("/se-connecter?error=true")
                .permitAll();

         http.logout().logoutSuccessUrl("/se-connecter?logout").permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }


    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return lanofromatiqueUserDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}