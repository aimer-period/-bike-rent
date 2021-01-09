package control;

public class Bike {
    private String name;
    private String id;
    private String content;

    public Bike() {
    }

    public Bike(String name, String id, String content) {
        this.name = name;
        this.id = id;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bike bike = (Bike) o;

        return id != null ? id.equals(bike.id) : bike.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Bike{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
