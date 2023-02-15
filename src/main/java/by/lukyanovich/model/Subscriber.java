package by.lukyanovich.model;

import java.util.List;
import java.util.Objects;

public class Subscriber {

    private int id;
    private String firstName;
    private String costOfSubscription;
    private int quantityOfClasses;
    private String typeOfBoughtSubscription;
    private List<Days> days;

    public Subscriber() {
    }

    public Subscriber(int id, String firstName, String costOfSubscription, int quantityOfClasses, String typeOfBoughtSubscription, List<Days> days) {
        this.id = id;
        this.firstName = firstName;
        this.costOfSubscription = costOfSubscription;
        this.quantityOfClasses = quantityOfClasses;
        this.typeOfBoughtSubscription = typeOfBoughtSubscription;
        this.days = days;
    }

    public int getId() {
        return id;
    }

    public int getQuantityOfClasses() {
        return quantityOfClasses;
    }

    public void setQuantityOfClasses(int quantityOfClasses) {
        this.quantityOfClasses = quantityOfClasses;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getTypeOfBoughtSubscription() {
        return typeOfBoughtSubscription;
    }

    public void setTypeOfBoughtSubscription(String typeOfBoughtSubscription) {
        this.typeOfBoughtSubscription = typeOfBoughtSubscription;
    }

    public List<Days> getDays() {
        return days;
    }

    public void setDays(List<Days> days) {
        this.days = days;
    }

    public String getCostOfSubscription() {
        return costOfSubscription;
    }

    public void setCostOfSubscription(String costOfSubscription) {
        this.costOfSubscription = costOfSubscription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subscriber that = (Subscriber) o;
        return id == that.id && quantityOfClasses == that.quantityOfClasses && Objects.equals(firstName, that.firstName) && Objects.equals(costOfSubscription, that.costOfSubscription) && typeOfBoughtSubscription.equals(that.typeOfBoughtSubscription) && Objects.equals(days, that.days);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, costOfSubscription, quantityOfClasses, typeOfBoughtSubscription, days);
    }

    @Override
    public String toString() {
        return "Subscriber{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", costOfSubscription='" + costOfSubscription + '\'' +
                ", quantityOfClasses=" + quantityOfClasses +
                ", typeOfBoughtSubscription='" + typeOfBoughtSubscription + '\'' +
                ", days=" + days +
                '}';
    }
}
