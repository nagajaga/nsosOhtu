package ohtu.domain;

public class Work {

    private Integer id;
    private String author;
    private String title;
    private String url;
    private String tags;
    private WorkType type;
    private boolean read;
    private Integer pages;
    private Integer currentPage;

    public Work(Integer id, String author, String title, String url, String tags, WorkType type, boolean read, Integer pages, Integer currentPage) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.url = url;
        this.tags = tags;
        this.type = type;
        this.read = read;
        this.pages = pages;
        this.currentPage = currentPage;
    }

    public Work(String author, String title, String url, int pages, String tags, WorkType type) {
        this.author = author;
        this.title = title;
        this.url = url;
        this.tags = tags;
        this.type = type;
        this.read = false;
        this.pages = pages;
        this.currentPage = 1;
    }

    public Work(String author, String title, String tags, int pages, WorkType type) {
        this.author = author;
        this.title = title;
        this.tags = tags;
        this.type = type;
        this.read = false;
        this.pages = pages;
        this.currentPage = 1;
    }

    public Integer getPages() {
        return this.pages;
    }

    public Integer getCurrentPage() {
        return this.currentPage;
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

    public String getCode() {
        return this.url;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getTags() {
        return this.tags;
    }

    public String getTitle() {
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

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public String toString() {
        if (type.equals(WorkType.WEBSITE)) {
            return "Website\n" + author + ": " + title + "\nURL: " + url + "\nTags: " + tags;
        }
        if (type.equals(WorkType.BOOK)) {
            return "Book\n" + author + ": " + title + "\nPages: " + pages + "\nCurrent page: " + currentPage + "\nTags: " + tags;
        }
        return null;
    }
}
