package ohtu.Domain;

public class Book implements Work {

    private Integer id;
    private String author;
    private String title;
    private String url;
    private String tags;
    private WorkType type;
    private boolean read;

    public Book(String author, String title, String tags, WorkType type) {
        this.author = author;
        this.title = title;
        this.tags = tags;
        this.type = type;
        this.read = false;
    }

    public boolean getRead() {
        return this.read;
    }

    public WorkType getType() {
        return type;
    }

    public Integer getId() {
        return id;
    }

    public String getUrl(){
        return this.url;
    }

    public String getAuthor(){
        return this.author;
    }

    public String getTags(){
        return this.tags;
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

    public void setUrl(String url) {
        this.url = url;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
    public void setRead(boolean read) {
        this.read = read;
    }

    public void setType(WorkType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Book\n" + author + ": " + title + "\nTags: " + tags;
    }
}
