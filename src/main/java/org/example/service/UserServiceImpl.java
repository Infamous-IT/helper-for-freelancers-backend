package org.example.service;

import org.example.dao.UserDAOImpl;
import org.example.dto.UserDTO;
import org.example.dto.UserWithOrderDTO;
import org.example.mapper.UserMapper;
import org.example.model.User;
import org.example.v1.searching.SearchCriteriaParser;
import org.example.v1.userFiltering.predicate.UserSpecificationsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserSpecificationsBuilder userSpecificationsBuilder;

    @Autowired
    private SearchCriteriaParser searchCriteriaParser;

    private final OrderService orderService;
    private final UserDAOImpl userDAO;
    private final UserMapper userMapper;

    public UserServiceImpl(UserDAOImpl userDAO, UserMapper userMapper, OrderService orderService) {
        this.userMapper = userMapper;
        this.userDAO = userDAO;
        this.orderService = orderService;
    }

    @Transactional(readOnly = true)
    public List<UserDTO> get() {
        return userMapper.userToUserDTOList(userDAO.getAll());
    }

    @Transactional
    @Override
    public UserDTO create(@Valid UserDTO userDTO) {
        User user = userMapper.userDTOToUser(userDTO);
        User createdUser = userDAO.create(user);
        return userMapper.userToUserDTO(createdUser);
    }

    @Transactional
    @Override
    public UserDTO update(@Valid UserDTO userDTO) {
        User user = userMapper.userDTOToUser(userDTO);
        userDAO.update(user);
        return userDTO;
    }

    @Transactional(readOnly = true)
    @Override
    public UserWithOrderDTO getById(UUID uuid) {
        User user = userDAO.getById(uuid);

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + uuid + " was not found.");
        }
        return userMapper.userToUserWithOrderDTO(user);
    }

    @Transactional
    @Override
    public void delete(UUID uuid) {
        userDAO.delete(uuid);
    }

//    public ResponsePage<UserDTO> get(Optional<String> search, Optional<Integer> pageNumber, Optional<Integer> pageSize, Sort sort ) {
//
//        Specification<User> filteringSpecification = null;
//        if(search.isPresent()) {
//            String search1 = search.get();
//            List<SearchCriteria> searchCriteria = searchCriteriaParser.parseSearchCriteria(search1, userSpecificationsBuilder.getFilterableProperties());
//            filteringSpecification = userSpecificationsBuilder.buildSpecification(searchCriteria);
//        }
//        return getResponsePage(
//                filteringSpecification,
//                pageNumber.orElse(DEFAULT_PAGE_NUMBER),
//                pageSize.orElse(DEFAULT_PAGE_SIZE), sort);
//    }
//
//    private ResponsePage<UserDTO> getResponsePage(Specification<User> filteringSpecification, Integer pageNumber, Integer pageSize, Sort sort) {
//        sort = getSortedByDefault(sort);
//        Pageable paging = PageRequest.of(pageNumber, pageSize, sort);
//        Page<User> pagedResult = userDAO.getUserWithPaginationSize(filteringSpecification, paging);
//        List<User> userList = pagedResult.getContent()
//                .stream()
//                .map(userMapper.userToUserDTOList(userDAO.getUserWithPaginationSize()))
//                .collect(Collectors.toList());
//        return new ResponsePage<UserDTO>(userMapper.userToUserDTOList(userList), pagedResult.getTotalElements());
//    }
//
//    private Sort getSortedByDefault(Sort sort) {
//        return sort.and(Sort.by( Sort.Direction.DESC, "lastName"));
//    }
}
