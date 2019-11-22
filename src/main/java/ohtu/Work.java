package ohtu;

public class Work {
    private Integer id;
    private String author;
    private String title;
    
    public Work(String author, String title) {
        this.author = author;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public String getAuthor(){
        return this.author;
    }

    public String getTitle(){
        return this.title;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAuthor(String name) {
        this.author = name;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    @Override
    public String toString() {
        return author + ": " + title;
    }
}