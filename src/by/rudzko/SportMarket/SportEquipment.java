package by.rudzko.SportMarket;

import by.rudzko.SportMarket.Extra.Strings;

/**
 * describes structure and behaviour of goods' copies
 *
 * @author Olga Rudzko
 * @see Category
 */

public class SportEquipment implements Comparable<SportEquipment> {

    private Category category;
    private String title;
    private double price;

    public SportEquipment(Category category, String title, double price) {

        this.category = category;
        this.title = title;
        this.price = price;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {

        return category;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SportEquipment other = (SportEquipment) obj;
        if (this.category == null || this.title == null || other.category == null || other.title == null) {
            return false;
        }

        return (!this.category.equals(other.category) || !this.title.equalsIgnoreCase(other.title));
    }

    @Override
    public int hashCode() {

        return 31 + ((this.category == null) ? 0 : this.category.hashCode()) +
                ((this.title == null) ? 0 : this.title.hashCode());
    }

    @Override
    public String toString() {

        return this.category.toString().concat(Strings.SEPARATOR2).concat(this.title).
                concat(Strings.CURRENCY).concat(Double.toString(price));
    }

    @Override
    public int compareTo(SportEquipment other) {

        if (this.category.compareTo(other.category) > 0) return 1;
        if (this.category.compareTo(other.category) < 0) return -1;
        if (this.title.compareToIgnoreCase(other.title) > 0) return 1;
        if (this.title.compareToIgnoreCase(other.title) < 0) return -1;

        return 0;
    }
}
