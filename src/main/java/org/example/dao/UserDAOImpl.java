package org.example.dao;

import org.example.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl extends AbstractDAO<User> implements UserDAO {
}