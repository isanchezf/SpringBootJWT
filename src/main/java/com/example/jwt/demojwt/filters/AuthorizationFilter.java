package com.example.jwt.demojwt.filters;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.example.jwt.demojwt.utils.TokenGenerator;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;

@Component
public class AuthorizationFilter extends OncePerRequestFilter {

    private final String HEADER = "Authorization";
    private final String PREFIX = "Bearer ";
    private final String identificadorAplicacion = "123456789"; // Se debe ingresar el valor del identificador de
    private final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCfeNZlOOO6BOJbZgs70Rof8yl/nJ4JwIw/T4xnorbYbUL8XqI7v+L5xl8mcXWIyLsiiE/luiE9GAGwcEjNbYGJTbn2fRMX5xmAC7W6zbCHTUVyTM1vwjELMN5c/QGTLgLvBRJyIONOQHPZK/B/2AEyvphRg3mVbieGskPWCX898QIDAQAB";
    private String JwtToken = ""; // aplicación generado

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
            final FilterChain filterChain) throws IOException, ServletException {

        try {
            boolean continuar = ExistsHeader(request, response);
            continuar = continuar ? Authorization(request, response) : false;
            if (continuar) {
                filterChain.doFilter(request, response);
            }
        }catch(ExpiredJwtException expiredException){
            response.sendError((int) HttpServletResponse.SC_FORBIDDEN, "Token Expired");
        }catch (Exception e) {
            response.sendError((int) HttpServletResponse.SC_UNAUTHORIZED, "Unauthorize");
            e.printStackTrace();
        }
    }

    private Boolean ExistsHeader(final HttpServletRequest request, final HttpServletResponse response)
            throws IOException {
        if (request.getHeader(HEADER) == null) {
            response.sendError((int) HttpServletResponse.SC_BAD_REQUEST, "Authorization header is required");
            return false;
        }
        JwtToken = request.getHeader(HEADER).replace(PREFIX, "");
        JwtToken = JwtToken.replace(PREFIX.toLowerCase(), "");
        JwtToken = JwtToken.replace(PREFIX.toUpperCase(), "");

        return true;
    }

    private Boolean Authorization(final HttpServletRequest request, final HttpServletResponse response)
            throws Exception {

        // final Claims claims = ManagerToken.DecodeTokenMethodTwo(JwtToken);
        final Claims claims = TokenGenerator.DecodeToken(JwtToken, PUBLIC_KEY);
        //final String identificadorAplicacionToken = claims.get("identificadorAplicacion", String.class);
        // if (identificadorAplicacionToken == null
        //         || !identificadorAplicacionToken.equalsIgnoreCase(identificadorAplicacion)) {
        //     response.sendError((int) HttpServletResponse.SC_UNAUTHORIZED, "Unauthorize");
        //     return false;
        // }
        if (claims == null) {
            response.sendError((int) HttpServletResponse.SC_UNAUTHORIZED, "Unauthorize");
            return false;
        }
        return true;
    }
}