package com.tutorial.apidemo.repositories;
import com.tutorial.apidemo.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

//import org.springframework.data.mongodb.repository.MongoRepository;
public interface ProductRepository extends JpaRepository< Product,Long> {

}
