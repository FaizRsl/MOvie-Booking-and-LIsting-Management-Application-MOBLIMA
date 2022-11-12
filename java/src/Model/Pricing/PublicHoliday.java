package Model.Pricing;

import java.io.Serializable;
import java.time.LocalDate;
/**
 * The Class PublicHoliday.
 */
public class PublicHoliday implements Serializable {
    /** The name. */
    private String name;
    /** The date. */
    private LocalDate date;
    /**
     * Instantiates a new public holiday.
     *
     * @param name the name
     * @param date the date
     */
    public PublicHoliday(String name, LocalDate date){
        this.name = name;
        this.date = date;
    }
    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName(){return name;}
    /**
     * Gets the date.
     *
     * @return the date
     */
    public LocalDate getDate(){return date;}
}
