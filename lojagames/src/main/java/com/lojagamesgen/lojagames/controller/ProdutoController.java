package com.lojagamesgen.lojagames.controller;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lojagamesgen.lojagames.model.ProdutoModel;
import com.lojagamesgen.lojagames.repository.ProdutoRepository;


@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping
	public ResponseEntity<List<ProdutoModel>> getAll(){
		return ResponseEntity.ok(produtoRepository.findAll());
	}
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoModel> geById (@PathVariable Long id){
		return produtoRepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse (ResponseEntity.notFound().build());
	}
	
	@GetMapping("/preco_inicial/{inico}/preco_final{fim}")
	public ResponseEntity<List<ProdutoModel>> getByprecoEntre(@PathVariable BigDecimal inicio, @PathVariable BigDecimal fim){
		return ResponseEntity.ok(produtoRepository.findByPrecoBetween(inicio,fim));
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<ProdutoModel>> getByNome (@PathVariable String nome){
		return ResponseEntity.ok(produtoRepository.findAllByNomeProdutoContainingIgnoreCase(nome));
	}
	
	
	@PostMapping
	public ResponseEntity <ProdutoModel> post (@RequestBody ProdutoModel produto){
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
}
	@PutMapping
	public ResponseEntity <ProdutoModel> put (@RequestBody ProdutoModel produto){
		return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produto));
}
	@DeleteMapping("/{id}")
	public void delete (@PathVariable Long id) {
		produtoRepository.deleteById(id);
		
	}
	
}

