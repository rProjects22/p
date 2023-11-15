package ca.gbc.productservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data  //accessors and mutators.
@AllArgsConstructor
@NoArgsConstructor
@Builder //allows us to instantiate objects all in line.
@Document(value = "product")
public class Product {

    //primary key for the product
    @Id
    private String Id;
    private String name;
    private String description;
    private BigDecimal price;
}
