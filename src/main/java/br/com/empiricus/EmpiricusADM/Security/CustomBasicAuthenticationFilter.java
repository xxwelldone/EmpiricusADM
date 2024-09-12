package br.com.empiricus.EmpiricusADM.Security;


import br.com.empiricus.EmpiricusADM.Model.User;
import br.com.empiricus.EmpiricusADM.Repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomBasicAuthenticationFilter extends OncePerRequestFilter {
    private static final String AUTHORIZATION = "Authorization";
    private static final String BASIC = "Basic ";
    private static final List<String> PERMITTED_URLS = Arrays.asList(
            "/swagger-ui.html",
            "/v3/api-docs",
            "/swagger-resources/",
            "/h2mem"
    );

    private final UserRepository userRepository;

    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String uri = request.getRequestURI();

        if (isPermittedUrl(uri)) {
            filterChain.doFilter(request, response);
            return;
        }

        if(isBasicAuthentication(request)){
            String[] credentials = decodeBase64(getHeader(request).replace(BASIC, ""))
                    .split(":");

            String username = credentials[0];
            String password = credentials[1];

            User user = userRepository.findByCpf(username);

            if(user == null){
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("User does not exist!");
                return;
            }

            boolean valid = checkPassword(user.getPassword(), password);

            if(!valid){
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("eeeeeeeeeeeeeeeeeee Password not match");
                return;
            }

            if (!user.isEh_admin()) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("User is not authorized (eh_admin=false)");
                return;
            }

            setAuthentication(user);
        }

        filterChain.doFilter(request, response);
    }

    private void setAuthentication(User user) {
        Authentication authentication = createAuthenticationToken(user);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private Authentication createAuthenticationToken(User user) {
        UserPrincipal userPrincipal = UserPrincipal.create(user);
        return new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());
    }

    //validaSenha
    private boolean checkPassword(String userPassword, String loginPassword) {
        return passwordEncoder().matches(loginPassword, userPassword);
    }

    //Decodifica
    private String decodeBase64(String base64) {
        byte[] decodeBytes = Base64.getDecoder().decode(base64);
        return new String(decodeBytes);
    }

    //encontram no header o auth
    private boolean isBasicAuthentication(HttpServletRequest request) {
        String header = getHeader(request);
        return header != null && header.startsWith(BASIC);
    }

    private String getHeader(HttpServletRequest request) {
        return request.getHeader(AUTHORIZATION);
    }
    private boolean isPermittedUrl(String uri) {
        return PERMITTED_URLS.stream().anyMatch(uri::startsWith);
    }
}