package com.proof.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Servicio de JWT (Json Web Token)
 * 
 * @autor David Orlando Velez Zamora
 */
@Tag(name = "JWT Service", description = "Servicio para la gestión de tokens JWT")
@Service
public class JwtService {

    private static final String SECRET_KEY = "586E3272357538782F413F4428472B4B6250655368566B597033733676397924";

    /**
     * Método para generar un token JWT
     * 
     * @param user
     * @return
     */
    @Operation(summary = "Generar un token JWT", description = "Genera un token JWT para un usuario dado.")
    public String getToken(UserDetails user) {
        return getToken(new HashMap<>(), user);
    }

    /**
     * Método para generar un token JWT
     * 
     * @param extraClaims
     * @param user
     * @return
     */
    private String getToken(Map<String, Object> extraClaims, UserDetails user) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Método para obtener la clave secreta
     * 
     * @return Clave secreta
     */
    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Método para obtener el nombre de usuario desde el token
     * 
     * @param token
     * @return Nombre de usuario
     */
    @Operation(summary = "Obtener el nombre de usuario desde el token", description = "Extrae el nombre de usuario contenido en el token JWT.")
    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    /**
     * Método para verificar si el token es válido
     * 
     * @param token
     * @param userDetails
     * @return true si el token es válido, false en caso contrario
     */
    @Operation(summary = "Verificar si el token es válido", description = "Valida si el token JWT corresponde al usuario y no ha expirado.")
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Método para obtener todas las reclamaciones del token
     * 
     * @param token
     * @return Claims
     */
    private Claims getAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Método para obtener una reclamación específica del token
     * 
     * @param token
     * @param claimsResolver
     * @return Claim específica
     */
    @Operation(summary = "Obtener una reclamación específica del token", description = "Obtiene un valor específico de las reclamaciones del token JWT.")
    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Método para obtener la fecha de expiración del token
     * 
     * @param token
     * @return Fecha de expiración del token
     */
    private Date getExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }

    /**
     * Método para verificar si el token ha expirado
     * 
     * @param token
     * @return true si el token ha expirado, false en caso contrario
     */
    public boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }
}
