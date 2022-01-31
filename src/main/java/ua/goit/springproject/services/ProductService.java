package ua.goit.springproject.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ua.goit.springproject.dto.ProductDto;
import ua.goit.springproject.model.Product;
import ua.goit.springproject.repositories.ProducerRepository;
import ua.goit.springproject.repositories.ProductRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;
    @Autowired
    private ProducerRepository producerRepository;
    @Autowired
    private ModelMapper mapper;

    public List<ProductDto> getAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public ProductDto get(Long id) {
        return repository.findById(id)
                .map(this::mapToDto)
                .orElseThrow();
    }

    @Transactional
    public void create(ProductDto dto) {
        repository.save(mapFromDto(dto));
    }

    public void update(Long id, ProductDto dto) {
        repository.findById(id)
                .map(product -> {
                    if (StringUtils.hasText(dto.getName()))
                        product.setName(dto.getName());

                    product.setCost(dto.getCost());

                    producerRepository.findById(dto.getProducerId())
                            .ifPresent(product::setProductProducer);

                    return product;
                }).ifPresent(
                        product -> repository.save(product)
                );
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private ProductDto mapToDto(Product product) {
        return mapper.map(product, ProductDto.class);
    }

    private Product mapFromDto(ProductDto dto) {
        Product product = mapper.map(dto, Product.class);

        producerRepository.findById(dto.getProducerId())
                .ifPresent(product::setProductProducer);

        return product;
    }

}
