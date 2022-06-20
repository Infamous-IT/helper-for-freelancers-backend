package org.example.service;

import org.example.dto.OrderDTO;
import org.example.dto.OrderWithNoteDTO;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    List<OrderDTO> get();

    OrderDTO create(OrderDTO orderDTO);

    OrderDTO update(OrderDTO orderDTO);

    OrderWithNoteDTO getById(UUID uuid);

    void delete(UUID uuid);
}
