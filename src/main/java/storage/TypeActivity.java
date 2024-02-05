package storage;

import java.util.Objects;

public class TypeActivity {
    //TODO: Konštruktory podľa potreby dorábať

    private int id;

    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypeActivity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public TypeActivity(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TypeActivity: " +
                "id= " + id + "\n" +
                " name= " + name + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TypeActivity that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
