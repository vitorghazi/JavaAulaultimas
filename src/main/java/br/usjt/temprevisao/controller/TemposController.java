package br.usjt.temprevisao.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;



import br.usjt.temprevisao.core.Forecast;
import  br.usjt.temprevisao.model.Cidade;
import  br.usjt.temprevisao.model.Clima;

import br.usjt.temprevisao.repository.*;
import br.usjt.temprevisao.model.Tempo;
import br.usjt.temprevisao.service.PrevisaoService;
import br.usjt.temprevisao.service.TemposService;

import java.util.List;


@Controller
public class TemposController {


    @Autowired
    private TemposService temposService;
    @Autowired
    private CidadeRepository cidadeRepo;
    
    @Autowired
    private PrevisaoService previsaoService;
    /*------------------------------------------------Model and View-----------------------------------------------------------------*/

    @GetMapping("/tempo")
    public ModelAndView listarPeriodos() {
        ModelAndView mv = new ModelAndView("lista_tempo");
        mv.addObject(new Tempo());
        //List<Tempo> tempos = repository.findAll();
        List<Tempo> tempos = temposService.listarTodos();
        mv.addObject("tempos", tempos);
       return mv;
    }
    /*------------------------------------------------Buscas-----------------------------------------------------------------*/
      @PostMapping("/buscarCidade")
    public ModelAndView buscarCidade(String nome){
        ModelAndView mv = new ModelAndView("lista_tempo");
        mv.addObject(new Tempo());
        //List<Tempo> tempos = repository.findAll();
           List<Tempo> tempos = temposService.buscarCidade(nome);
        //Future<List<Tempo>> tempos = peridosService.buscarCidade(nome);
        mv.addObject("tempos", tempos);
        return mv;
    }

    @PostMapping("/buscarLateLon")
    public ModelAndView buscarLateLon(Double lat, Double lon){
        ModelAndView mv = new ModelAndView("lista_tempo");
        mv.addObject(new Tempo());
        List<Tempo> tempos = temposService.buscarLateLon(lat,lon);
        mv.addObject("tempos", tempos);
        return mv;
    }

    @PostMapping("/tempo")
    public ModelAndView Home() {
        ModelAndView mv = new ModelAndView("lista_tempo");
        mv.addObject(new Tempo());
        List<Tempo> tempos = temposService.listarTodos();
        mv.addObject("tempos", tempos);
        return mv;
    }
    
    @GetMapping(value = "/buscarPrev/cidade/{cidadeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String consume(@PathVariable long cidadeId) {
        Cidade cidade = cidadeRepo.findById(cidadeId).get(); 
        Forecast forecast = previsaoService.obter(cidade);
        List<Clima> climas = previsaoService.toClimaList(forecast, cidade);
        previsaoService.saveClimas(climas);
        return "redirect:/tempo";
    }
   
}
