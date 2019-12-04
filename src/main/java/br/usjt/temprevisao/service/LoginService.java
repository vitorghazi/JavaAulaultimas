package br.usjt.temprevisao.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.usjt.temprevisao.model.Usuario;
import br.usjt.temprevisao.repository.UsuarioRepository;

@Service
public class LoginService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public boolean logar(Usuario usuario) {
        return usuarioRepository.findOneByLoginAndSenha(usuario.getLogin(), usuario.getSenha()) != null;
    }

}
