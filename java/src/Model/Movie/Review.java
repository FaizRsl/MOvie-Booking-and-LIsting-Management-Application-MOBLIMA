package Model.Movie;

import java.io.Serializable;

public class Review implements Serializable {
    private String reviewerName;

    private double rating;

    private String reviewContent;

    public Review(String reviewerName, double rating, String reviewContent){
        this.reviewerName = reviewerName;
        this.rating = rating;
        this.reviewContent = reviewContent;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    @Override
    public String toString() {
        String returnString = "";
        returnString += "" + "Reviewer: " + this.reviewerName + "\n";
        returnString += "   " + "Rating: " + String.format("%.1f",this.rating) + "\n";
        returnString += "   " + "Content: " + this.reviewContent;
        return returnString;
    }
}
