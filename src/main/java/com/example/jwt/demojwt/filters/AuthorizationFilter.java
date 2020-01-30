package com.example.jwt.demojwt.filters;

import java.io.IOException;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.interfaces.Claim;
import com.example.jwt.demojwt.utils.ManagerToken;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;

@Component
public class AuthorizationFilter extends OncePerRequestFilter {

    private final String HEADER = "Authorization";
    private final String PREFIX = "Bearer ";
    private final String identificadorAplicacion = "123456789"; // Se debe ingresar el valor del identificador de
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
        } catch (Exception e) {
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
        return true;
    }

    private Boolean Authorization(final HttpServletRequest request, final HttpServletResponse response)
            throws Exception {
        final Claims claims = ManagerToken.DecodeTokenMethodTwo(JwtToken);
        final String identificadorAplicacionToken = claims.get("identificadorAplicacion", String.class);
        if (identificadorAplicacionToken == null
                || !identificadorAplicacionToken.equalsIgnoreCase(identificadorAplicacion)) {
            response.sendError((int) HttpServletResponse.SC_UNAUTHORIZED, "Unauthorize");
            return false;
        }
        return true;
    }
}