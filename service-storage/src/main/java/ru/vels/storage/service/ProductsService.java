package ru.vels.storage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vels.storage.dto.BookingDto;
import ru.vels.storage.dto.GoodDto;
import ru.vels.storage.dto.ProductDto;
import ru.vels.storage.entity.BookingId;
import ru.vels.storage.entity.Bookings;
import ru.vels.storage.entity.Product;
import ru.vels.storage.exceptions.BookingException;
import ru.vels.storage.rep.BookingRepository;
import ru.vels.storage.rep.ProductsRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductsService {

    @Autowired
    ProductsRepository productsRepository;
    @Autowired
    BookingRepository bookingRepository;

    public List<Product> getProducts(String name, Boolean availableOnly,
                                     Boolean enabledOnly,
                                     Integer page, Integer size,
                                     Integer minWeight, Integer maxWeight,
                                     String article) {
        Specification<Product> spec = Specification.where(null);

        if (name != null) spec = spec.and(hasName(name));
        if (availableOnly) spec = spec.and(hasAvailableOnly());
        if (enabledOnly) spec = spec.and(hasEnabledOnly());
        if (maxWeight != null && maxWeight != 0) spec = spec.and(hasMaxWeight(maxWeight));
        if (minWeight != null && minWeight != 0) spec = spec.and(hasMinWeight(minWeight));

        if (article != null) spec = spec.and(hasArticle(article));

        Page<Product> products = productsRepository.findAll(spec, PageRequest.of(page, size));
        return products.getContent();


    }

    public Product getProduct(String id) {
        Optional<Product> productById = productsRepository.findById(id);
        if (productById.isPresent()) {
            Product product = productById.get();
            return product;
        }
        return null;
    }

    public void addProduct(ProductDto product) {
        Product products = new Product();
        products.setId(UUID.randomUUID().toString());
        products.setDimensions(product.getDimensions());
        products.setName(product.getName());
        products.setEnabled(product.getEnabled());
        products.setArticle(product.getArticle());
        products.setWeight(product.getWeight());
        products.setWeightUnits(product.getWeightUnits());
        products.setCount(product.getCount());
        products.setDimensionsUnits(product.getDimensionsUnits());
        productsRepository.save(products);

    }

    public void addBooking(BookingDto bookingDto) {
        BookingId bookingId = new BookingId();
        bookingId.setProduct(bookingDto.getProductId());
        bookingId.setOrderId(bookingDto.getOrderId());

        Bookings bookings = new Bookings();
        bookings.setId(bookingId);
        bookings.setCount(bookingDto.getCount());
        bookingRepository.save(bookings);

    }

    public String determineEnableTrue(String id) {
        Optional<Product> productById = productsRepository.findById(id);
        if (productById.isPresent()) {
            Product product = productById.get();
            product.setEnabled(true);
            productsRepository.save(product);
            return "200";
        }
        return null;
    }

    public String determineEnableFalse(String id) {
        Optional<Product> productById = productsRepository.findById(id);
        if (productById.isPresent()) {
            Product product = productById.get();
            product.setEnabled(false);
            productsRepository.save(product);
            return "200";
        }
        return null;
    }

    public String refillGood(String id, Long count) {
        Optional<Product> productById = productsRepository.findById(id);
        if (productById.isPresent()) {
            Product product = productById.get();
            product.setCount(product.getCount() + count);
            productsRepository.save(product);
            return "200";
        }
        return null;
    }

    public String writeOff(String id, Long count) {
        Optional<Product> productById = productsRepository.findById(id);
        if (productById.isPresent()) {
            Product product = productById.get();
            product.setCount(product.getCount() - count);
            productsRepository.save(product);
            return "200";
        }
        return null;
    }

    @Transactional
    public void deleteBooking(String bookingId) {

        List<Bookings> bookings = bookingRepository.findById_OrderId(bookingId);

        for (Bookings booking : bookings) {
            Product product = productsRepository.getReferenceById(booking.getId().getProduct().getId());
            refillGood(product.getId(), booking.getCount());
            bookingRepository.delete(booking);
        }

    }


    @Transactional
    public void booking(List<GoodDto> goodDtos) {
        for (GoodDto goodDto : goodDtos) {
            Optional<Product> productOptional = productsRepository.findById(goodDto.getId());
            if (productOptional.isPresent()) {
                Product product = productOptional.get();
                if (product.getCount() >= goodDto.getCount()) {
                    writeOff(product.getId(), goodDto.getCount());
                    BookingDto bookingDto = new BookingDto();
                    bookingDto.setCount(goodDto.getCount());
                    bookingDto.setProductId(product);
                    bookingDto.setOrderId(UUID.randomUUID().toString());
                    addBooking(bookingDto);
                } else {
                    throw new BookingException("products count");
                }
            } else {
                throw new BookingException("products null");
            }
        }
    }


    public static Specification<Product> hasName(String name) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("name"), name));
    }

    public static Specification<Product> hasAvailableOnly() {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.gt(root.get("count"), 0));
    }

    public static Specification<Product> hasId(String id) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("id"), id));
    }

    public static Specification<Product> hasEnabledOnly() {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.isTrue(root.get("enabled")));
    }

    public static Specification<Product> hasMaxWeight(Integer maxWeight) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.le(root.get("weight"), maxWeight));
    }

    public static Specification<Product> hasMinWeight(Integer minWeight) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.ge(root.get("weight"), minWeight));
    }

    public static Specification<Product> hasArticle(String article) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("article"), article));
    }


}
