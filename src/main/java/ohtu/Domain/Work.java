package ohtu.Domain;

public interface Work {

    boolean getRead();
    Integer getId();
    String getAuthor();
    String getTags();
    String getTitle();
    String getUrl();
    WorkType getType();
    void setRead(boolean read);
    void setId(Integer id);
    void setAuthor(String author);
    void setTags(String tags);
    void setTitle(String title);
    void setUrl(String url);
    void setType(WorkType type);
    @Override
    String toString();

}
