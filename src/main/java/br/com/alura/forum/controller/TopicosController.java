package br.com.alura.forum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.controller.form.TopicoForm;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

	@Autowired
	private TopicoRepository topicoRepository;
	
	@Autowired
	private CursoRepository cursoRepository;
	
	@GetMapping
	public List<TopicoDto> lista(String nomeCurso){
		List<Topico> topicos;
		if(nomeCurso!=null) {
			topicos = topicoRepository.findByCursoNome(nomeCurso);
		}else {
			topicos = topicoRepository.findAll();
		}
		return TopicoDto.converter(topicos);	
	}
	
	@PostMapping
	public void cadastrar(@RequestBody TopicoForm topicoForm) {
		Topico topico = topicoForm.converter(cursoRepository);
		topicoRepository.save(topico);
	}
	
}