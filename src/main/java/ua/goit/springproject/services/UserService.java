package ua.goit.springproject.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ua.goit.springproject.dto.UserDto;
import ua.goit.springproject.model.Role;
import ua.goit.springproject.model.User;
import ua.goit.springproject.repositories.RoleRepository;
import ua.goit.springproject.repositories.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ModelMapper mapper;

    public List<User> getAll() {
        return repository.findAll();
    }

    public List<UserDto> getAllDto() {
        return getAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public UserDto get(Long id) {
        return repository.findById(id)
                .map(this::mapToDto)
                .orElseThrow();
    }

    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    public Role getRoleByName(String name) {
        return roleRepository.getByName(name);
    }

    @Transactional
    public void create(UserDto dto) {
        User user = mapFromDto(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        roleRepository.findById(dto.getRoleId())
                .ifPresent(user::setRole);

        repository.save(user);
    }

    public void registerNewAccount(UserDto dto) {
        long userRoleId = roleRepository.getByName("User").getId();

        dto.setRoleId(userRoleId);

        create(dto);
    }

    public void update(Long id, UserDto dto) {
        repository.findById(id)
                .map(user -> {
                    if (StringUtils.hasText(dto.getEmail()))
                        user.setEmail(dto.getEmail());

                    if (StringUtils.hasText(dto.getFirstName()))
                        user.setFirstName(dto.getFirstName());

                    if (StringUtils.hasText(dto.getLastName()))
                        user.setLastName(dto.getLastName());

                    if (StringUtils.hasText(dto.getPassword()))
                        user.setPassword(passwordEncoder.encode(dto.getPassword()));

                    roleRepository.findById(dto.getRoleId())
                            .ifPresent(user::setRole);

                    return user;
                }).ifPresent(
                        user -> repository.save(user)
                );
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private UserDto mapToDto(User user) {
        return mapper.map(user, UserDto.class);
    }

    private User mapFromDto(UserDto userDto) {
        return mapper.map(userDto, User.class);
    }

}
