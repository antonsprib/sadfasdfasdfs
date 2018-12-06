package lv.helloit.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final DefaultAuthEntryPoint entryPoint;
    private final SecurityPropertiesBean securityProperties;

    @Autowired
    public SecurityConfiguration(DefaultAuthEntryPoint entryPoint, SecurityPropertiesBean securityProperties) {
        this.entryPoint = entryPoint;
        this.securityProperties = securityProperties;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.inMemoryAuthentication()
                    .withUser(securityProperties.getAdminName())
                        .password("{noop}" + securityProperties.getAdminPassword())
                        .authorities("ADMIN")
                    .and()
                    .withUser(securityProperties.getUserName())
                        .password("{noop}" + securityProperties.getUserPassword())
                        .authorities("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/users.html").permitAll()
                .antMatchers("/users**").hasAuthority("ADMIN")
                .antMatchers("/tasks**").authenticated()
                .and()
                .httpBasic().authenticationEntryPoint(entryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
