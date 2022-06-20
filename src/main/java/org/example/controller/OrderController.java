package org.example.controller;

import org.example.dto.OrderDTO;
import org.example.dto.OrderWithNoteDTO;
import org.example.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = OrderController.BASE_URL)
public class OrderController {

    public static final String BASE_URL = ServiceAPIUrl.VERSION_PATH + "/orders";

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<OrderDTO> get() {
        return orderService.get();
    }

    @GetMapping(value = "/{id}")
    public OrderWithNoteDTO getById(@PathVariable UUID id) {
        return orderService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDTO create(@Valid @RequestBody OrderDTO orderDTO) {
        orderService.create(orderDTO);
        return orderDTO;
    }

    @PutMapping(value = "/{id}")
    public OrderDTO update(@Valid @RequestBody OrderDTO orderDTO) {
        orderService.update(orderDTO);
        return orderDTO;
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable UUID id) {
        orderService.delete(id);
    }
}
