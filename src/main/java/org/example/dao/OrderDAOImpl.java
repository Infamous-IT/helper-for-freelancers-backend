package org.example.dao;

import org.example.model.Order;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDAOImpl extends AbstractDAO<Order> implements OrderDAO {
}
