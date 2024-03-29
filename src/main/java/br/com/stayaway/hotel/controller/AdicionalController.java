package br.com.stayaway.hotel.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.stayaway.hotel.impl.AdicionalServiceImpl;
import br.com.stayaway.hotel.impl.HotelServiceImpl;
import br.com.stayaway.hotel.model.request.AdicionalRequest;
import br.com.stayaway.hotel.model.response.AdicionalResponse;
import br.com.stayaway.hotel.service.AdicionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.stayaway.hotel.model.domain.Adicional;

@RestController
@RequestMapping("api/adicional")
public class AdicionalController {

	@Autowired
	private AdicionalService adicionalService;

    @PostMapping("/lista")
	public List<AdicionalResponse> obterPorListaIdEQuantidade(@RequestBody List<AdicionalRequest> lista) {
		List<AdicionalResponse> respostas = new ArrayList<>();
		for (AdicionalRequest adicionalReq : lista) {
			Adicional adicional = this.adicionalService.obterPorCodigo(adicionalReq.getId());
			AdicionalResponse adicionalResponse = new AdicionalResponse(
					adicional.getId(),
					adicionalReq.getQuantidade(),
					adicional.getValor(),
					adicional.getObs(),
					adicional.getTipo().name());
			respostas.add(adicionalResponse);
		}
		return respostas;
	}

	@GetMapping
	public List<Adicional> obterTodos(){
		return this.adicionalService.buscarTodos();
	}
	
	@DeleteMapping
	public void deleteById(@RequestParam("id") String id) {
		this.adicionalService.deleteById(id);
	}
	
	@PostMapping
	public Adicional criar(@RequestBody Adicional adicional) {
		return this.adicionalService.criar(adicional);
	}
	
	@PutMapping
	public void atualizar(@RequestBody Adicional adicional) {
		this.adicionalService.atualizar(adicional);
	}
	
	@GetMapping("/{id}")
	public Adicional obterPorCodigo(@PathVariable String id) {
		return this.adicionalService.obterPorCodigo(id);
	}

}
