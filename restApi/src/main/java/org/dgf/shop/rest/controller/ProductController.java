package org.dgf.shop.rest.controller;

import lombok.RequiredArgsConstructor;
import org.dgf.shop.rest.common.ResponseError;
import org.dgf.shop.rest.common.ResponseOk;
import org.dgf.shop.rest.dto.ProductDto;
import org.dgf.shop.rest.dto.ProductNewDto;
import org.dgf.shop.rest.model.Product;
import org.dgf.shop.service.product.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping(value = "/product")
@RequiredArgsConstructor
@Validated
public class ProductController {

	public static final String PRODUCT_NOT_FOUND = "Product not found";

	private final ProductService service;

	@PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Product> create(@Valid @RequestBody() ProductNewDto dto) {
		Product resp = service.create(dto);
		return new ResponseEntity<>(resp, HttpStatus.CREATED);
	}

	@PostMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Object> update(@Valid @RequestBody() ProductDto dto) {
		Optional<ResponseEntity<Object>> resp = service.update(dto)
				.map(ResponseOk::new);
		return resp.orElseGet(() -> new ResponseError(PRODUCT_NOT_FOUND));
	}

	@GetMapping(path = "/find/{nameLikeFilter}")
	ResponseEntity<List<Product>> find(@RequestParam(required = false) String nameLikeFilter) {
		return new ResponseOk<>(service.find(nameLikeFilter));
	}

}
