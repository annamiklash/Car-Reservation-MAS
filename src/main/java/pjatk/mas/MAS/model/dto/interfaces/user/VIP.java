package pjatk.mas.MAS.model.dto.interfaces.user;

/**
 * Interface containing getters and setters for fields that are required to have by an object of class User that is a Customer and specifically VIP
 */
public interface VIP extends Customer {

    Integer getDiscount();

    void setDiscount(Integer discount);
}
