package ohtu;

public class Work {
    private Integer id;
    private String author;
    private String title;
    private String url;
    private String tags;
    private boolean read;
    
    public Work(String author, String title, String url, String tags) {
        this.author = author;
        this.title = title;
        this.url = url;
        this.tags = tags;
        this.read = false;
    }
    
    public boolean getRead() {
        return this.read;
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
    
    @Override
    public String toString() {
        return author + ": " + title + "\nURL: " + url + "\nTags: " + tags;
    }
}