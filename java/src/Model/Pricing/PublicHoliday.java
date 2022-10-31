package Model.Pricing;

import java.time.LocalDate;

public class PublicHoliday {

    private String name;

    private LocalDate date;

    public PublicHoliday(String name, LocalDate date){
        this.name = name;
        this.date = date;
    }

    public String getName(){return name;}

    public LocalDate getDate(){return date;}
}
