package br.usjt.temprevisao.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.usjt.temprevisao.model.Cidade;
import br.usjt.temprevisao.repository.CidadeRepository;



@RestController
@RequestMapping("/cidades")
public class CidadeResource {
	@Autowired
	private CidadeRepository cidadeRepo;
	
	@GetMapping("/{id}")
    public Cidade buscarPeloId(@PathVariable Long id) {
        return cidadeRepo.getOne(id);
    }
	
	@GetMapping("/lista")
	public List<Cidade> todas() {
		return cidadeRepo.findAll();
	}
	
	
	
	@PostMapping("/salvar")
	public ResponseEntity<Cidade> salvar(@RequestBody Cidade cidade, HttpServletResponse response) {
		Cidade c = cidadeRepo.save(cidade);
		URI uri = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/{id}")
				.buildAndExpand(c.getId())
				.toUri();
		return ResponseEntity.created(uri).body(c);
	}
	
}
