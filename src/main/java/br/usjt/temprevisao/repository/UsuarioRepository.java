package br.usjt.temprevisao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.usjt.temprevisao.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Usuario findOneByLoginAndSenha (String login, String senha);

}
