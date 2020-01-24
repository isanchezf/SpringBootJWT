package com.example.jwt.demojwt.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.jwt.demojwt.utils.ManagerToken;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;


@Component
public class AuthorizationFilter extends OncePerRequestFilter {

    private final String HEADER = "Authorization";
    private final String PREFIX = "Bearer ";
    private final String SECRET = "lazo123"; // Se debe ingresar el Secret Key suministrado
    private final String identificadorAplicacion = "123456789"; // Se debe ingresar el valor del identificador de aplicación generado

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain)
            throws ServletException, IOException {

        boolean continuar = ExistsHeader(request, response);
        continuar = continuar ? Authorization(request, response) : false;
        if (continuar) { filterChain.doFilter(request, response); }
    }

    private Boolean ExistsHeader(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        if (request.getHeader(HEADER) == null) {
            response.sendError((int) HttpServletResponse.SC_BAD_REQUEST, "Authorization header is required");
            return false;
        }
        return true;
    }

    private Boolean Authorization(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        try {
            String jwtToken = request.getHeader(HEADER).replace(PREFIX, "");
            Claims claims = ManagerToken.DeserializeToken(SECRET, jwtToken);
            String identificadorAplicacionToken = claims.get("identificadorAplicacion", String.class);
            if (identificadorAplicacionToken == null || !identificadorAplicacionToken.equalsIgnoreCase(identificadorAplicacion)){
                response.sendError((int) HttpServletResponse.SC_FORBIDDEN, "Unauthorize");
                return false;
            }
            return true;
        } catch(ExpiredJwtException e){
            response.sendError((int) HttpServletResponse.SC_FORBIDDEN, "Token Expired");
            return false;
        }
    }
}