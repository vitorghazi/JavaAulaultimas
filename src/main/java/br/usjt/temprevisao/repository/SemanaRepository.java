package br.usjt.temprevisao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.usjt.temprevisao.model.DiaSemana;


public interface SemanaRepository extends JpaRepository<DiaSemana, Long> {

}


