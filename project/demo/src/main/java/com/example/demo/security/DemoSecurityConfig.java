package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {

   /* @Bean
    public InMemoryUserDetailsManager userDetailsManager() {

        UserDetails jhon = User.builder()
                .username("jhon")
                .password("{noop}test123")
                .roles("Employee")
                .build();

        UserDetails marry = User.builder()
                .username("marry")
                .password("{noop}test123")
                .roles("Employee", "Manager")
                .build();

        UserDetails susan = User.builder()
                .username("susan")
                .password("{noop}test123")
                .roles("Employee", "Manager", "Admin")
                .build();

        // Return the InMemoryUserDetailsManager with each user added only once
        return new InMemoryUserDetailsManager(jhon, marry, susan);
    }

*/

    @Bean
    public UserDetailsManager userDetailsManager(DataSource datasource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(datasource);

        // Set the queries for loading user details and authorities
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select user_id, pw, active from members where user_id=?"
        );
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select user_id, role from roles where user_id=?"
        );

        return jdbcUserDetailsManager;
    }



@Bean
    public SecurityFilterChain filterChain(HttpSecurity http ) throws Exception {


        http.authorizeHttpRequests(configurer ->

                configurer
                        .requestMatchers("/").hasRole("EMPLOYEE")
                        .requestMatchers("/employees/list/**").hasRole("MANAGER")
                        .requestMatchers("/employees/showFormAdd/**").hasRole("ADMIN")
                        .anyRequest().authenticated()

                )

                .formLogin(form ->
                          form
                                  .loginPage("/showMyLoginPage")
                                  .loginProcessingUrl("/authenticateTheUser")
                                  .permitAll()
                )
                                  .logout(logout ->logout.permitAll()



                )

                .exceptionHandling(
                        configurer ->
                                configurer.accessDeniedPage("/access-denied")
                )



        ;


return http.build();
}













}
