package by.lukyanovich.model;

import java.util.Objects;

public class Subscription {
    private int id;
    private String typeOfSubscription;
    private int cost;
    private int quantityOfClasses;

    public Subscription() {
    }

    public Subscription(int id, String typeOfSubscription, int cost, int quantityOfClasses) {
        this.id = id;
        this.typeOfSubscription = typeOfSubscription;
        this.cost = cost;
        this.quantityOfClasses = quantityOfClasses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeOfSubscription() {
        return typeOfSubscription;
    }

    public void setTypeOfSubscription(String typeOfSubscription) {
        this.typeOfSubscription = typeOfSubscription;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getQuantityOfClasses() {
        return quantityOfClasses;
    }

    public void setQuantityOfClasses(int quantityOfClasses) {
        this.quantityOfClasses = quantityOfClasses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subscription that = (Subscription) o;
        return id == that.id && cost == that.cost && quantityOfClasses == that.quantityOfClasses && typeOfSubscription.equals(that.typeOfSubscription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, typeOfSubscription, cost, quantityOfClasses);
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", typeOfSubscription='" + typeOfSubscription + '\'' +
                ", cost=" + cost +
                ", quantityOfClasses=" + quantityOfClasses +
                '}';
    }
}
