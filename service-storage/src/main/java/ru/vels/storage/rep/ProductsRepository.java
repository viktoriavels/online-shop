package ru.vels.storage.rep;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.vels.storage.entity.Product;
@Repository
public interface ProductsRepository extends JpaRepository<Product,String>, JpaSpecificationExecutor<Product> {

}
