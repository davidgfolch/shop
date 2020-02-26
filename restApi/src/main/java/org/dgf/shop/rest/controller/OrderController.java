package org.dgf.shop.rest.controller;

import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.dgf.shop.rest.common.ResponseOk;
import org.dgf.shop.rest.dto.OrderNewDto;
import org.dgf.shop.rest.model.Order;
import org.dgf.shop.rest.model.OrderException;
import org.dgf.shop.rest.model.Product;
import org.dgf.shop.service.order.OrderService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController()
@RequestMapping(value = "/order")
@RequiredArgsConstructor
@Validated
public class OrderController {

	private final OrderService service;

	@PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Order<Product>> create(@Valid @RequestBody() OrderNewDto dto) throws OrderException {
		Order<Product> res = service.create(dto);
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}

	@GetMapping(path = "/find")
	public ResponseEntity<List<Order<Product>>> find(
			@ApiParam(defaultValue = "2020-01-01T00:00:00") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @RequestParam LocalDateTime from,
			@ApiParam(defaultValue = "2050-12-31T00:00:00") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @RequestParam LocalDateTime to) {
		return new ResponseOk<>(service.find(from,to));
	}

}
