package com.sparta.spartamongodbfinalproject.security.config;

import com.sparta.spartamongodbfinalproject.model.repositories.SessionRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtService jwtService;
  private final UserDetailsService userDetailsService;

  private final SessionRepository sessionRepository;

  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain
  ) throws ServletException, IOException {
    System.out.println("doFilterInternal, JwtAuthenticationFilter");
    String authHeader = request.getHeader("Authorization");
    if (authHeader == null) {
      authHeader = request.getParameter("Authorization");
      System.out.println("Authorization header is equal to null");
      System.out.println(authHeader);
    }
    final String jwt;
    final String userEmail;
    System.out.println("Auth Header: " + authHeader);
    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
      System.out.println("If authHeader and authParameter is equal to null, then do this");
      filterChain.doFilter(request, response);
      return;
    }
    jwt = authHeader.substring(7);
    System.out.println(jwt);
    userEmail = jwtService.extractUsername(jwt);
    System.out.println("User email:" + userEmail);
    if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
      var isTokenValid = sessionRepository.findByJwt(jwt).isPresent();
      System.out.println("Is token valid:" + isTokenValid);
      if (jwtService.isTokenValid(jwt, userDetails) && isTokenValid) {
        System.out.println("This will confirm that the token is extra valid");
        System.out.println(userDetails.getAuthorities());
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
            userDetails,
            null,
            userDetails.getAuthorities()
        );
        authToken.setDetails(
            new WebAuthenticationDetailsSource().buildDetails(request)
        );
        SecurityContextHolder.getContext().setAuthentication(authToken);
      }
    }
    filterChain.doFilter(request, response);
  }
}
