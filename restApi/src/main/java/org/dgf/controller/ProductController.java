package org.dgf.controller;

import lombok.RequiredArgsConstructor;
import org.dgf.common.ResponseError;
import org.dgf.common.ResponseOk;
import org.dgf.dto.ProductDto;
import org.dgf.model.Product;
import org.dgf.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping(value = "/product")
@RequiredArgsConstructor
class ProductController {

	private final ProductService service;

	@PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Product> create(@RequestBody() ProductDto dto) {
		Product product = service.create(dto);
		return new ResponseEntity<>(product, HttpStatus.CREATED);
	}

	@PostMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Object> update(@RequestBody() ProductDto dto) {
		Optional<ResponseEntity<Object>> resp = service.update(dto)
				.map(ResponseOk::new);
		return resp.orElseGet(() -> new ResponseError("Product not found"));
	}

	@GetMapping(path = "/find/{nameLikeFilter}")
	ResponseEntity<List<Product>> find(@RequestParam(required = false) String nameLikeFilter) {
		return new ResponseOk<>(service.find(nameLikeFilter));
	}

}
