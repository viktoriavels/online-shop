package ru.vels.storage.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "PRODUCTS")
public class Product implements Serializable {
    @Id
    private String id;
    @OneToMany(mappedBy = "id.product", fetch = FetchType.LAZY)
    private List<Bookings> bookings;
    @Column(name = "ARTICLE", unique = true, nullable = false, length = 25)
    private String article;
    @Column(name = "NAME", unique = true, nullable = false, length = 100)
    private String name;
    @Column(name = "WEIGHT", nullable = false, length = 100)
    private Long weight;
    @Column(name = "WEIGHT_UNITS", nullable = false, length = 100)
    private String weightUnits;
    @Column(name = "COUNT", nullable = false, length = 100)
    private Long count;
    @Column(name = "DIMENSIONS", nullable = false, length = 100)
    private String dimensions;
    @Column(name = "DIMENSIONS_UNITS", nullable = false, length = 100)
    private String dimensionsUnits;
    @Column(name = "ENABLED", nullable = false, length = 100)
    private Boolean enabled;

    public Product() {
    }

    public String getId() {
        return this.id;
    }

    public List<Bookings> getBookings() {
        return this.bookings;
    }

    public String getArticle() {
        return this.article;
    }

    public String getName() {
        return this.name;
    }

    public Long getWeight() {
        return this.weight;
    }

    public String getWeightUnits() {
        return this.weightUnits;
    }

    public Long getCount() {
        return this.count;
    }

    public String getDimensions() {
        return this.dimensions;
    }

    public String getDimensionsUnits() {
        return this.dimensionsUnits;
    }

    public Boolean getEnabled() {
        return this.enabled;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBookings(List<Bookings> bookings) {
        this.bookings = bookings;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public void setWeightUnits(String weightUnits) {
        this.weightUnits = weightUnits;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public void setDimensionsUnits(String dimensionsUnits) {
        this.dimensionsUnits = dimensionsUnits;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Product)) return false;
        final Product other = (Product) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$bookings = this.getBookings();
        final Object other$bookings = other.getBookings();
        if (this$bookings == null ? other$bookings != null : !this$bookings.equals(other$bookings)) return false;
        final Object this$article = this.getArticle();
        final Object other$article = other.getArticle();
        if (this$article == null ? other$article != null : !this$article.equals(other$article)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$weight = this.getWeight();
        final Object other$weight = other.getWeight();
        if (this$weight == null ? other$weight != null : !this$weight.equals(other$weight)) return false;
        final Object this$weightUnits = this.getWeightUnits();
        final Object other$weightUnits = other.getWeightUnits();
        if (this$weightUnits == null ? other$weightUnits != null : !this$weightUnits.equals(other$weightUnits))
            return false;
        final Object this$count = this.getCount();
        final Object other$count = other.getCount();
        if (this$count == null ? other$count != null : !this$count.equals(other$count)) return false;
        final Object this$dimensions = this.getDimensions();
        final Object other$dimensions = other.getDimensions();
        if (this$dimensions == null ? other$dimensions != null : !this$dimensions.equals(other$dimensions))
            return false;
        final Object this$dimensionsUnits = this.getDimensionsUnits();
        final Object other$dimensionsUnits = other.getDimensionsUnits();
        if (this$dimensionsUnits == null ? other$dimensionsUnits != null : !this$dimensionsUnits.equals(other$dimensionsUnits))
            return false;
        final Object this$enabled = this.getEnabled();
        final Object other$enabled = other.getEnabled();
        if (this$enabled == null ? other$enabled != null : !this$enabled.equals(other$enabled)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Product;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $bookings = this.getBookings();
        result = result * PRIME + ($bookings == null ? 43 : $bookings.hashCode());
        final Object $article = this.getArticle();
        result = result * PRIME + ($article == null ? 43 : $article.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $weight = this.getWeight();
        result = result * PRIME + ($weight == null ? 43 : $weight.hashCode());
        final Object $weightUnits = this.getWeightUnits();
        result = result * PRIME + ($weightUnits == null ? 43 : $weightUnits.hashCode());
        final Object $count = this.getCount();
        result = result * PRIME + ($count == null ? 43 : $count.hashCode());
        final Object $dimensions = this.getDimensions();
        result = result * PRIME + ($dimensions == null ? 43 : $dimensions.hashCode());
        final Object $dimensionsUnits = this.getDimensionsUnits();
        result = result * PRIME + ($dimensionsUnits == null ? 43 : $dimensionsUnits.hashCode());
        final Object $enabled = this.getEnabled();
        result = result * PRIME + ($enabled == null ? 43 : $enabled.hashCode());
        return result;
    }

    public String toString() {
        return "Products(id=" + this.getId() + ", bookings=" + this.getBookings() + ", article=" + this.getArticle() + ", name=" + this.getName() + ", weight=" + this.getWeight() + ", weightUnits=" + this.getWeightUnits() + ", count=" + this.getCount() + ", dimensions=" + this.getDimensions() + ", dimensionsUnits=" + this.getDimensionsUnits() + ", enabled=" + this.getEnabled() + ")";
    }
}
