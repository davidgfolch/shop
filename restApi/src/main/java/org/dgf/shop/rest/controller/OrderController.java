package org.dgf.shop.rest.controller;

import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.dgf.shop.rest.common.ResponseOk;
import org.dgf.shop.rest.dto.OrderNewDto;
import org.dgf.shop.rest.model.Order;
import org.dgf.shop.rest.model.Product;
import org.dgf.shop.service.order.OrderService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController()
@RequestMapping(value = "/order")
@RequiredArgsConstructor
@Validated
public class OrderController {

	private final OrderService service;

	@PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Order<Product>> create(@Valid @RequestBody() OrderNewDto dto) {
		Order<Product> res = service.create(dto);
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}

	@GetMapping(path = "/find/{dateFrom}/{dateTo}")
	ResponseEntity<List<Order<Product>>> find(
			@ApiParam(defaultValue = "2020-01-01") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate from,
			@ApiParam(defaultValue = "2050-12-31") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate to) {
		return new ResponseOk<>(service.find(from,to));
	}

}
