package com.gs.GameStore.controller.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "products", schema = "game_store")
public class Product implements Serializable {

//    private static final long serialVersionUID = -1000119078147252957L;

    @Id
    @Basic(optional = false)
    @Column(name = "product_code", length = 20, unique = true)
    private Long productCode;

    @Basic(optional = false)
    @Column(name = "product_name")
    private String name;

    @Basic(optional = false)
    @Column(name = "price")
    private double price;

//    @Lob
//    @Column(name = "image", length = Integer.MAX_VALUE, nullable = true)
//    private byte[] image;

    public Product() {
    }

    public Long getProductCode() {
        return productCode;
    }

    public void setProductCode(Long productCode) {
        this.productCode = productCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    //    public byte[] getImage() {
//        return image;
//    }
//
//    public void setImage(byte[] image) {
//        this.image = image;
//    }


    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Product{" +
                "productCode=" + productCode +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
