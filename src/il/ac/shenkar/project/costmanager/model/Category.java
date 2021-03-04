package il.ac.shenkar.project.costmanager.model;

public class Category {
    private String name;
    private int id;

    public Category(String name) {
        setName(name);
        setId();
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setId() {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }
}
