package org.example.service;

import org.example.dao.OrderDAOImpl;
import org.example.dto.OrderDTO;
import org.example.dto.OrderWithNoteDTO;
import org.example.mapper.OrderMapper;
import org.example.model.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDAOImpl orderDAO;
    private final OrderMapper orderMapper;

    public OrderServiceImpl(OrderDAOImpl orderDAO, OrderMapper orderMapper) {
        this.orderDAO = orderDAO;
        this.orderMapper = orderMapper;
    }

    @Transactional(readOnly = true)
    @Override
    public List<OrderDTO> get() {
        return orderMapper.orderToOrderDTOList(orderDAO.getAll());
    }

    @Transactional
    @Override
    public OrderDTO create(@Valid OrderDTO orderDTO) {
        Order order = orderMapper.orderDTOToOrder(orderDTO);
        Order createdOrder = orderDAO.create(order);
        return orderMapper.orderToOrderDTO(createdOrder);
    }

    @Transactional
    @Override
    public OrderDTO update(@Valid OrderDTO orderDTO) {
        Order order = orderMapper.orderDTOToOrder(orderDTO);
        orderDAO.update(order);
        return orderDTO;
    }

    @Transactional(readOnly = true)
    @Override
    public OrderWithNoteDTO getById(UUID uuid) {
        Order order = orderDAO.getById(uuid);

        if (order == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order with id " + uuid + "was not found.");
        }
        return orderMapper.orderToOrderWithNoteDTO(order);
    }

    @Transactional
    @Override
    public void delete(UUID uuid) {
        orderDAO.delete(uuid);
    }
}