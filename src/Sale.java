import java.io.Serializable;

/**
 * Classe que representa uma Sale.
 *
 * @author Rafael
 */
public class Sale implements Serializable, Comparable<Sale> {

    private Long id;

    private Long idItem;

    private Long qtyItems;

    private Double priceItem;

    private String sellerName;

    public Sale(Long id, Long idItem, Long qtyItems, Double priceItem, String sellerName) {
        this.id = id;
        this.idItem = idItem;
        this.qtyItems = qtyItems;
        this.priceItem = priceItem;
        this.sellerName = sellerName;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Sale)) {
            return false;
        }
        Sale other = (Sale) obj;
        if (this.id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!this.id.equals(other.id)) {
            return false;
        }
        return true;
    }
    public int compareTo(Sale other) {
    	Double total = this.qtyItems * this.priceItem;
    	Double totalOther = other.getQtyItems() * other.getPriceItem();
    	return total.compareTo(totalOther);
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getIdItem() {
        return this.idItem;
    }
    public void setIdItem(Long idItem) {
        this.idItem = idItem;
    }
    public Long getQtyItems() {
        return this.qtyItems;
    }
    public void setQtyItems(Long qtyItems) {
        this.qtyItems = qtyItems;
    }
    public Double getPriceItem() {
        return this.priceItem;
    }
    public void setPriceItem(Double priceItem) {
        this.priceItem = priceItem;
    }
    public String getSellerName() {
        return this.sellerName;
    }
    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }
}
