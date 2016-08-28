package tm.agbaria.reddit.reddit;

/**
 * Created by 3la2 on 24/08/2016.
 */
public class Reddit {
    private String author;
    private long created;
    private String url;
    private String title;
    private String thumbnail;
    private int score;
    private int numComments;
    private String urlComments;

    public Reddit(String author, long created, String url, String title, String thumbnail, int score, int numComments, String urlComments) {
        this.author = author;
        this.created = created;
        this.url = url;
        this.title = title;
        this.thumbnail = thumbnail;
        this.score = score;
        this.numComments = numComments;
        this.urlComments = urlComments;
    }

    public String getAuthor() {
        return author;
    }

    public long getCreated() {
        return created;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public int getScore() {
        return score;
    }

    public int getNumComments() {
        return numComments;
    }

    public String getUrlComments() {
        return urlComments;
    }
}
