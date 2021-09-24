package ua.epam.radchenko.persistence.entity;

import ua.epam.radchenko.util.type.Status;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Class that represents exhibition
 */
public class Exhibition implements Serializable {
    private Integer exhibitionId;
    private String exhibitionName;
    private String description;
    private BigDecimal price;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private String theme;
    private Status exhibitionStatus;
    private Integer hall;

    public static class Builder {
        private final Exhibition exhibition;

        public Builder() { exhibition = new Exhibition();}

        public Builder setExhibitionId(Integer exhibitionId) {
            exhibition.setExhibitionId(exhibitionId);
            return this;
        }

        public Builder setExhibitionName(String exhibitionName) {
            exhibition.setExhibitionName(exhibitionName);
            return this;
        }

        public Builder setDescription(String description) {
            exhibition.setDescription(description);
            return this;
        }

        public Builder setPrice(BigDecimal price) {
            exhibition.setPrice(price);
            return this;
        }

        public Builder setDateStart(LocalDate dateStart) {
            exhibition.setDateStart(dateStart);
            return this;
        }

        public Builder setDateEnd(LocalDate dateEnd) {
            exhibition.setDateEnd(dateEnd);
            return this;
        }

        public Builder setTheme(String theme) {
            exhibition.setTheme(theme);
            return this;
        }

        public Builder setExhibitionStatus(Status exhibitionStatus) {
            exhibition.setExhibitionStatus(exhibitionStatus);
            return this;
        }

        public Builder setHall(Integer hall) {
            exhibition.setHall(hall);
            return this;
        }

        public Exhibition build() {return exhibition;}
    }

    public static Builder newBuilder() {return new Builder();}

    public Exhibition(){}

    public Integer getExhibitionId() {
        return exhibitionId;
    }

    public void setExhibitionId(Integer exhibitionId) {
        this.exhibitionId = exhibitionId;
    }

    public String getExhibitionName() {
        return exhibitionName;
    }

    public void setExhibitionName(String exhibitionName) {
        this.exhibitionName = exhibitionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Status getExhibitionStatus() {
        return exhibitionStatus;
    }

    public void setExhibitionStatus(Status exhibitionStatus) {
        this.exhibitionStatus = exhibitionStatus;
    }

    public Integer getHall() {
        return hall;
    }

    public void setHall(Integer hall) {
        this.hall = hall;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exhibition that = (Exhibition) o;
        return Objects.equals(exhibitionId, that.exhibitionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exhibitionId);
    }

    @Override
    public String toString() {
        return "Exhibition{" +
                "exhibitionId=" + exhibitionId +
                ", exhibitionName='" + exhibitionName + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                ", theme='" + theme + '\'' +
                ", exhibitionStatus=" + exhibitionStatus +
                ", hall=" + hall +
                '}';
    }
}
