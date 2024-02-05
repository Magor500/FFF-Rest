package storage;

import java.util.Objects;

public class Breed {
    //TODO: Konštruktory podľa potreby dorábať

    private Long id;
    public enum Brand {
        M, P
    }

    private Brand brand;

    private String name;

    private int size;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Breed: " +
                "id= " + id +
                " brand= " + brand +
                " name= " + name +
                ", size=" + size + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Breed breed)) return false;
        return size == breed.size && Objects.equals(id, breed.id) && brand == breed.brand && Objects.equals(name, breed.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, name, size);
    }
}
