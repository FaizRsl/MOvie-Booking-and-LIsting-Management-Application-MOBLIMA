package Model.Movie;

import java.io.Serializable;

/**
 * The Class Review, for defining review objects.
 */
public class Review implements Serializable {
	
	/** The reviewer name. */
    private String reviewerName;
    
    /** The rating. */
    private double rating;
    
    /** The review content. */
    private String reviewContent;
    
    /**
     * Instantiates a new review.
     *
     * @param reviewerName the reviewer name
     * @param rating the rating
     * @param reviewContent the review content
     */
    public Review(String reviewerName, double rating, String reviewContent){
        this.reviewerName = reviewerName;
        this.rating = rating;
        this.reviewContent = reviewContent;
    }
    
    /**
     * Gets the reviewer name.
     *
     * @return the reviewer name
     */
    public String getReviewerName() {
        return reviewerName;
    }
    
    /**
     * Sets the reviewer name.
     *
     * @param reviewerName the new reviewer name
     */
    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }
    
    /**
     * Gets the rating.
     *
     * @return the rating
     */
    public double getRating() {
        return rating;
    }
    
    /**
     * Sets the rating.
     *
     * @param rating the new rating
     */
    public void setRating(double rating) {
        this.rating = rating;
    }
    
    /**
     * Gets the review content.
     *
     * @return the review content
     */
    public String getReviewContent() {
        return reviewContent;
    }
    
    /**
     * Sets the review content.
     *
     * @param reviewContent the new review content
     */
    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }
    
    /**
     * Returns a String of review data which is well-formatted.
     *
     * @return the string
     */
    @Override
    public String toString() {
        String returnString = "";
        returnString += "" + "Reviewer: " + this.reviewerName + "\n";
        returnString += "   " + "Rating: " + String.format("%.1f",this.rating) + "\n";
        returnString += "   " + "Content: " + this.reviewContent;
        return returnString;
    }
}
