package br.com.companhia.aeroporto.controller;

import br.com.companhia.aeroporto.domain.Usuario;
import br.com.companhia.aeroporto.dto.AuthenticationDTO;
import br.com.companhia.aeroporto.dto.LoginResponseDTO;
import br.com.companhia.aeroporto.dto.RegisterDTO;
import br.com.companhia.aeroporto.repository.UsuarioRepository;
import br.com.companhia.aeroporto.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        if (this.usuarioRepository.findByUsername(data.username()) == null)
            return ResponseEntity.badRequest().body("Usuário não encontrado");

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
        if (this.usuarioRepository.findByUsername(data.username()) != null) return ResponseEntity.badRequest().body("Usuário já cadastrado no sistema");

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        Usuario newUsuario = new Usuario(data.username(), encryptedPassword, data.role());

        this.usuarioRepository.save(newUsuario);

        return ResponseEntity.ok("Cadastro efeutado com sucesso");
    }
}
