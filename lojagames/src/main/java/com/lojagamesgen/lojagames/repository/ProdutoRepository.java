package com.lojagamesgen.lojagames.repository;

	import java.math.BigDecimal;
	import java.util.List;

	import org.springframework.data.jpa.repository.JpaRepository;
	import org.springframework.data.repository.query.Param;
	import org.springframework.stereotype.Repository;

import com.lojagamesgen.lojagames.model.ProdutoModel;



	@Repository
	public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {

			public List <ProdutoModel> findByPrecoBetween(@Param("inicio") BigDecimal inicio, @Param("fim") BigDecimal fim);
			public List<ProdutoModel> findAllByNomeProdutoContainingIgnoreCase (@Param ("nomeProduto") String nomeProduto);

		}