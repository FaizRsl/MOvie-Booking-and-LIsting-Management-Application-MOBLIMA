import java.util.UUID;

public class Rating {

    private UUID id;
    private int rating;
    private String review;

    public Rating(){

    }

    public Rating(int rating, String review){
        this.id = UUID.randomUUID();
        this.rating = rating;
        this.review = review;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }



}
