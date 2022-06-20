package org.example.service;

import org.example.dao.OrderDAOImpl;
import org.example.dto.OrderDTO;
import org.example.dto.OrderWithNoteDTO;
import org.example.mapper.OrderMapper;
import org.example.model.Order;
import org.example.model.enumerated.Category;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @InjectMocks
    OrderServiceImpl orderService;

    @Mock
    OrderDAOImpl orderDAO;

    @Autowired
    private OrderMapper orderMapper;

    @Test
    void getAll() {
        List<Order> orderList = new ArrayList<>();
        Order order1 = new Order("Title for order 1", "Description for order 1", BigDecimal.valueOf(1500),
                LocalDateTime.now(ZoneOffset.UTC), LocalDateTime.now(ZoneOffset.UTC).plusDays(3),
                Category.TRANSLATE);
        Order order2 = new Order("Title for order 2", "Description for order 2", BigDecimal.valueOf(4000),
                LocalDateTime.now(ZoneOffset.UTC), LocalDateTime.now(ZoneOffset.UTC).plusDays(6),
                Category.PROGRAMMING);
        Order order3 = new Order("Title for order 3", "Description for order 3", BigDecimal.valueOf(1000),
                LocalDateTime.now(ZoneOffset.UTC), LocalDateTime.now(ZoneOffset.UTC).plusDays(2),
                Category.DESIGN);

        orderList.add(order1);
        orderList.add(order2);
        orderList.add(order3);

        when(orderDAO.getAll()).thenReturn(orderList);

        List<OrderDTO> orderDTOList = orderService.get();
        assertEquals(3, orderDTOList.size());
        verify(orderDAO, times(1)).getAll();
    }

    @Test
    void create() {
        Order order = new Order("Title for order", "Description for order", BigDecimal.valueOf(4000),
                null, null, Category.PROGRAMMING);

        orderService.create(orderMapper.orderToOrderDTO(order));
        assertEquals("Title for order", order.getTitle());
        assertEquals("Description for order", order.getDescription());
        assertEquals(BigDecimal.valueOf(4000), order.getPrice());
        assertNull(order.getStartDate());
        assertNull(order.getEndDate());
        assertEquals("PROGRAMMING", order.getCategories().toString());

        verify(orderDAO, times(1)).create(order);
    }

    @Test
    void update() {
        Order order = new Order("Title for order", "Description for order", BigDecimal.valueOf(4000),
                null, null, Category.PROGRAMMING);

        orderService.update(orderMapper.orderToOrderDTO(order));
        assertEquals("Title for order", order.getTitle());
        assertEquals("Description for order", order.getDescription());
        assertEquals(BigDecimal.valueOf(4000), order.getPrice());
        assertNull(order.getStartDate());
        assertNull(order.getEndDate());
        assertEquals("PROGRAMMING", order.getCategories().toString());

        verify(orderDAO, times(1)).update(order);
    }

    @Test
    void getById() {
        when(orderDAO.getById(UUID.fromString("65fe885a-35ef-4105-9d06-2373759b2c13")))
                .thenReturn(new Order("Title for order", "Description for order", BigDecimal.valueOf(4000),
                        null, null,
                        Category.PROGRAMMING));

        OrderWithNoteDTO orderDTO = orderService.getById(UUID.fromString("65fe885a-35ef-4105-9d06-2373759b2c13"));

        assertEquals("Title for order", orderDTO.getTitle());
        assertEquals("Description for order", orderDTO.getDescription());
        assertEquals(BigDecimal.valueOf(4000), orderDTO.getPrice());
        assertNull(orderDTO.getStartDate());
        assertNull(orderDTO.getEndDate());
        assertEquals("PROGRAMMING", orderDTO.getCategories().toString());
    }

    @Test
    void delete() {
        UUID id = UUID.fromString("65fe885a-35ef-4105-9d06-2373759b2c15");

        willDoNothing().given(orderDAO).delete(id);

        orderService.delete(id);

        verify(orderDAO, times(1)).delete(id);
    }
}