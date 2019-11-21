package ohtu;

public class Work {
    private String author;
    private String title;
    
    public Work(String author, String title) {
        this.author = author;
        this.title = title;
    }

    public String getAuthor(){
        return this.author;
    }

    public String getTitle(){
        return this.title;
    }

    public void setAuthor(String name) {
        this.author = name;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}