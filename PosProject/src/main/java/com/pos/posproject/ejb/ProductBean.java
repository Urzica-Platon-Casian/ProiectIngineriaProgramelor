package com.pos.posproject.ejb;

import com.pos.posproject.common.ProductDetails;
import com.pos.posproject.common.StatisticProductDetails;
import com.pos.posproject.entity.LineItem;
import com.pos.posproject.entity.Product;
import com.pos.posproject.entity.ProductCatalog;
import com.pos.posproject.entity.Sale;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Monica
 */
@Stateless
public class ProductBean {

    private static final Logger LOG = Logger.getLogger(ProductBean.class.getName());

    @PersistenceContext
    private EntityManager em;

    public ProductDetails findById(Integer productId) {
        Product product = em.find(Product.class, productId);
        return new ProductDetails(product.getId(), product.getBarcode(),
                product.getName(), product.getDescription(), product.getPrice(),
                product.getCategory(), product.getProductCatalog().getProductCatalogName(), product.getQuantity());
    }

    public List<ProductDetails> getAllProducts() {
        LOG.info("getAllProducts");
        try {
            Query query = em.createQuery("SELECT c FROM Product c WHERE c.isArchived = false");
            List<Product> products = (List<Product>) query.getResultList();
            return copyProductsToDetails(products);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }
    
     public List<StatisticProductDetails> getAllProductsForReport() {
        LOG.info("getAllProductsForReport");
        try {
            Query query = em.createQuery("SELECT c FROM Product c WHERE c.isArchived = false");
            List<Product> products = (List<Product>) query.getResultList();
            return copyFromProductToReportProductDetails(products);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public List<ProductDetails> getAllProductsFromCatalog(Integer ProductCatalogId) {
        LOG.info("getAllProducts");
        try {
            TypedQuery<Product> typedQuery = em.createQuery("SELECT c FROM Product c where c.productCatalog.id = :id and c.isArchived = false", Product.class)
                    .setParameter("id", ProductCatalogId);
            List<Product> products = typedQuery.getResultList();
            return copyProductsToDetails(products);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }
    
    public List<ProductDetails> getAllProductsForStockReport(Integer stock) {
        LOG.info("getAllProductsForStockReport");
        try {
            TypedQuery<Product> typedQuery = em.createQuery("SELECT c FROM Product c where c.quantity <= :stock and c.isArchived = false", Product.class)
                    .setParameter("stock", stock);
            List<Product> products = typedQuery.getResultList();
            return copyProductsToDetails(products);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }
    
    public int getNumberOfSales(Integer productId){
        LOG.info("getAllProductsForStockReport");
        int ct = 0;
        int ok =0;
        try {
            TypedQuery<Sale> typedQuery = em.createQuery("SELECT c FROM Sale c", Sale.class);
            List<Sale> sales = typedQuery.getResultList();
            for(Sale sale : sales)
            {
                ok = 0;
                Collection<LineItem> lineItems = sale.getSaleProducts();
                for(LineItem lineItem : lineItems)
                {
                    if(lineItem.getProduct().getId() == productId && ok == 0)
                    {
                        ct++;
                        ok = 1;
                    }
                }
            }
            return ct;
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }
    private List<StatisticProductDetails> copyFromProductToReportProductDetails(List<Product> products) {
        List<StatisticProductDetails> detailsList = new ArrayList<>();
        for (Product product : products) {
            StatisticProductDetails statisticProductDetails = new StatisticProductDetails(product.getId(),
                    product.getName(), getNumberOfSales(product.getId()));
            detailsList.add(statisticProductDetails);
        }
        return detailsList;
    }

    private List<ProductDetails> copyProductsToDetails(List<Product> products) {
        List<ProductDetails> detailsList = new ArrayList<>();
        for (Product product : products) {
            ProductDetails productDetails = new ProductDetails(product.getId(),
                    product.getBarcode(),
                    product.getName(),
                    product.getDescription(),
                    product.getPrice(),
                    product.getCategory(),
                    product.getProductCatalog().getProductCatalogName(),
                    product.getQuantity());
            detailsList.add(productDetails);
        }
        return detailsList;
        
        
    }

    public void createProduct(String barcode, String name, String description, Double price, String category, int quantity, Integer productCatalogId) {
        LOG.info("createProduct");
        Product product = new Product();
        product.setBarcode(barcode);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setCategory(category);
        product.setQuantity(quantity);
        product.setIsArchived(false);

        ProductCatalog productCatalog = em.find(ProductCatalog.class, productCatalogId);
        productCatalog.getProducts().add(product);
        product.setProductCatalog(productCatalog);

        em.persist(product);
    }

    public void updateProduct(int productId, String barcode, String name, String description, Double price, String category, int quantity, Integer productCatalogId) {

        LOG.info("updateProduct");
        Product product = em.find(Product.class, productId);
        product.setBarcode(barcode);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setCategory(category);
        product.setQuantity(quantity);
        
        ProductCatalog oldProductCatalog = product.getProductCatalog();
        oldProductCatalog.getProducts().remove(product);

        ProductCatalog productCatalog = em.find(ProductCatalog.class, productCatalogId);
        productCatalog.getProducts().add(product);
        product.setProductCatalog(productCatalog);
    }

    public void deleteProductsByIds(List<Integer> ids) {
        LOG.info("deleteProductsByIds");
        for (Integer id : ids) {
            Product product = em.find(Product.class, id);
            ProductCatalog ProductCatalog = product.getProductCatalog();
            ProductCatalog.getProducts().remove(product);
            product.setIsArchived(true);
            //em.remove(product);
        }
    }
    
    public Collection<String> findProductName(Collection<Integer> productIds) {
        LOG.info("findProductName");
        List<String> productNames = (List<String>) em.createQuery("SELECT u.name FROM Product u WHERE u.id IN ?1")
                .setParameter(1, productIds)
                .getResultList();
        return productNames;
    }
}
