package org.example.mapper;

import org.example.dto.OrderDTO;
import org.example.dto.OrderWithNoteDTO;
import org.example.model.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderDTO orderToOrderDTO(Order order);

    List<OrderDTO> orderToOrderDTOList(List<Order> orders);

    OrderWithNoteDTO orderToOrderWithNoteDTO(Order orders);

    Order orderDTOToOrder(OrderDTO orderDTO);

}
