package com.example.dichvugiacsay.Model;

public class DonHang {
    private int  quantitty,  price;
    private String img, name, description;

    public DonHang(int quantitty, int price, String img, String name, String description) {
        this.quantitty = quantitty;
        this.price = price;
        this.img = img;
        this.name = name;
        this.description = description;
    }

    public int getQuantitty() {
        return quantitty;
    }

    public void setQuantitty(int quantitty) {
        this.quantitty = quantitty;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "DonHang{" +
                "quantitty=" + quantitty +
                ", price=" + price +
                ", img='" + img + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
