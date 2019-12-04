package br.usjt.temprevisao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.usjt.temprevisao.model.Clima;



public interface PrevisaoRepository extends JpaRepository<Clima, Long> {

}
