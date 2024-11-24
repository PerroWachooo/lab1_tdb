package tbd.lab1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import tbd.lab1.dtos.TokenResponseDTO;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtUtil;
    private final UsuarioService usuarioService;

    @Autowired
    public AuthService(AuthenticationManager authenticationManager, JwtService jwtUtil, UsuarioService usuarioService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.usuarioService = usuarioService;
    }

    public TokenResponseDTO authenticate(String username, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,
                        password
                )
        );

        Integer idUsuario = usuarioService.getUsuarioByEmail(username).getId_usuario();
        String jwtToken = jwtUtil.generateToken(username);
        String jwtRefreshToken = jwtUtil.generateRefreshToken(username);
        return new TokenResponseDTO(jwtToken, jwtRefreshToken, idUsuario);

    }
}