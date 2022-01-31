package ua.goit.springproject.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ua.goit.springproject.dto.ProducerDto;
import ua.goit.springproject.model.Producer;
import ua.goit.springproject.repositories.ProducerRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProducerService {

    @Autowired
    private ProducerRepository repository;
    @Autowired
    private ModelMapper mapper;

    public List<ProducerDto> getAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public ProducerDto get(Long id) {
        return repository.findById(id)
                .map(this::mapToDto)
                .orElseThrow();
    }

    @Transactional
    public void create(ProducerDto dto) {
        repository.save(mapFromDto(dto));
    }

    public void update(Long id, ProducerDto dto) {
        repository.findById(id)
                .map(producer -> {
                    if (StringUtils.hasText(dto.getName()))
                        producer.setName(dto.getName());

                    return producer;
                }).ifPresent(
                        producer -> repository.save(producer)
                );
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private ProducerDto mapToDto(Producer producer) {
        return mapper.map(producer, ProducerDto.class);
    }

    private Producer mapFromDto(ProducerDto dto) {
        return mapper.map(dto, Producer.class);
    }

}
