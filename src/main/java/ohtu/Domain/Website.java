package ohtu.Domain;

public class Website extends Book {

    public Website(String author, String title, String url, String tags, WorkType type) {
        super(author, title, tags, type);
        super.setUrl(url);
    }

    @Override
    public String toString() {
        return "Website\n" + getAuthor() + ": " + getTitle() + "\nURL: " + getUrl() + "\nTags: " + getTags();
        }
}
