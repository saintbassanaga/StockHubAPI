package tech.saintbassanaga.stockhubapi.services;

import tech.saintbassanaga.stockhubapi.dtos.ProductDetailsDto;
import tech.saintbassanaga.stockhubapi.dtos.ProductDto;
import tech.saintbassanaga.stockhubapi.models.Product;

import java.util.List;
import java.util.UUID;

/**
 * Created by saintbassanaga {saintbassanaga}
 * In the Project StockHubAPI at Thu - 9/19/24
 */

public interface ProductService {

   public Product createProduct(ProductDto productDto);
   public List<ProductDto> getAllProduct();
   public ProductDto getProductByUuid(UUID productUuid);
   public String deleteProduct(UUID productId);
   public String multipleDelete(List<UUID> uuidList);
   public ProductDetailsDto updateProduct(Product product, UUID productId);

}
