package com.irctc.config;
import jakarta.servlet.FilterChain; import jakarta.servlet.http.*;
import org.springframework.web.filter.OncePerRequestFilter; import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken; import org.springframework.security.core.userdetails.UserDetails;
import java.io.IOException;
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil; private final CustomUserDetailsService userDetailsService;
    public JwtAuthenticationFilter(JwtUtil jwtUtil, CustomUserDetailsService uds){ this.jwtUtil=jwtUtil; this.userDetailsService=uds; }
    @Override protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, jakarta.servlet.ServletException {
        String header = req.getHeader("Authorization"); if(header!=null && header.startsWith("Bearer ")){ String token = header.substring(7); try{ String username = jwtUtil.getUsernameFromToken(token); if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){ UserDetails userDetails = userDetailsService.loadUserByUsername(username); if(jwtUtil.validateToken(token, userDetails.getUsername())){ var auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()); SecurityContextHolder.getContext().setAuthentication(auth); } } }catch(Exception e){} } chain.doFilter(req,res); }
}
