package ua.goit.springproject.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ua.goit.springproject.dto.RoleDto;
import ua.goit.springproject.model.Role;
import ua.goit.springproject.repositories.RoleRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {

    @Autowired
    private RoleRepository repository;
    @Autowired
    private ModelMapper mapper;

    public List<RoleDto> getAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public RoleDto getDto(Long id) {
        return repository.findById(id)
                .map(this::mapToDto)
                .orElseThrow();
    }

    public Role getById(Long id) {
        return repository.findById(id)
                .orElseThrow();
    }

    public Role getByName(String name) {
        return repository.getByName(name);
    }

    @Transactional
    public void create(RoleDto dto) {
        repository.save(mapFromDto(dto));
    }

    public void update(Long id, RoleDto dto) {
        repository.findById(id)
                .map(role -> {
                    if (StringUtils.hasText(dto.getName()))
                        role.setName(dto.getName());

                    return role;
                }).ifPresent(
                        role -> repository.save(role)
                );
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private RoleDto mapToDto(Role role) {
        return mapper.map(role, RoleDto.class);
    }

    private Role mapFromDto(RoleDto roleDto) {
        return mapper.map(roleDto, Role.class);
    }

}
