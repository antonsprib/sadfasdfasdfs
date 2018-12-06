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
    private final CustomAuthenticationProvider authenticationProvider;

    @Autowired
    public SecurityConfiguration(DefaultAuthEntryPoint entryPoint,
                                 SecurityPropertiesBean securityProperties,
                                 CustomAuthenticationProvider authenticationProvider) {
        this.entryPoint = entryPoint;
        this.securityProperties = securityProperties;
        this.authenticationProvider = authenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider);
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
