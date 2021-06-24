package pjatk.mas.MAS.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import pjatk.mas.MAS.constants.RegexConstants;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * Address entity that stores information about city, street name, street number and zip code
 */
@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity(name = "address")
public class Address implements Serializable {

    /**
     * Unique ID for entity Address
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_address")
    Long id;

    /**
     * Name of the city
     */
    @NotBlank(message = "City is mandatory")
    @Pattern(regexp = RegexConstants.CITY_REGEX)
    @Length(max = 30)
    @Column
    String city;

    /**
     * Name of the street
     */
    @NotBlank(message = "Street name is mandatory")
    @Pattern(regexp = RegexConstants.STREET_REGEX)
    @Length(max = 30)
    @Column(name = "street_name")
    String streetName;

    /**
     * The number of a building
     */
    @NotNull(message = "Street number is mandatory")
    @Column(name = "street_number")
    @Min(1)
    Integer streetNumber;


    /**
     * Zip code for the address
     */
    @NotBlank(message = "Zip code is mandatory")
    @Pattern(regexp = RegexConstants.PL_ZIP_REGEX)
    @Length(max = 6)
    @Column(name = "zip_code")
    String zipCode;

    /**
     * @return address in a text string form
     */
    @Override
    public String toString() {
        return streetName + " " + streetNumber + ", " + city + " " + zipCode;
    }

}
