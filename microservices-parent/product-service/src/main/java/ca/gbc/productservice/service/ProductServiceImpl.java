package ca.gbc.productservice.service;

import ca.gbc.productservice.dto.ProductRequest;
import ca.gbc.productservice.dto.ProductResponse;
import ca.gbc.productservice.model.Product;
import ca.gbc.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor //constructor injection.
@Slf4j //wrapper for locker to do simple lock statements to console or lock file.
public class ProductServiceImpl implements ProductService{

    //inject the repository to the product service.
    private final ProductRepository productRepository;
    private final MongoTemplate mongoTemplate;

    @Override
    public void createProduct(ProductRequest productRequest) {

        //creating the product in the memory.
        // {} is how we do the log in lombok.
        log.debug("Creating a new product {}", productRequest.getName());
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        //passing the product to the product document.
        productRepository.save(product);

        //doing another log.
        //going to replace {} with productId
        log.info("Product {} is saved", product.getId());
    }

    @Override
    public String updateProduct(String productId, ProductRequest productRequest) {

        //good practice, but your log files can grow. So you would want to be more selective.
        log.info("Updating a product with id {}", productId);

        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(productId));
        Product product = mongoTemplate.findOne(query, Product.class);

        if(product != null){
            product.setName(productRequest.getName());
            product.setDescription(productRequest.getDescription());
            product.setPrice(productRequest.getPrice());

            //update it to the actual database.
            log.info("Product {} is updated", product.getId());
            return productRepository.save(product).getId();
        }
        return productId.toString();
    }

    @Override
    public void deleteProduct(String productId) {

        log.info("Product {} is deleted", productId);
        productRepository.deleteById(productId);
    }

    @Override
    public List<ProductResponse> getAllProducts() {

        log.info("Returning a list of products");

        List<Product> products = productRepository.findAll();

        //map calls the mapToProductResponse function to generate (in this case) a list of productResponses.
        //stream works on a collection of data.
        return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product){
        return ProductResponse.builder()
                .Id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
