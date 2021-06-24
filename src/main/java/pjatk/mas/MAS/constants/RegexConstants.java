package pjatk.mas.MAS.constants;

/**
 * Regex constant values used in the app for validation
 */
public class RegexConstants {

    /**
     * Regex for polish zip codes. (ex: 01-157)
     */
    public static final String PL_ZIP_REGEX = "^\\d{2}[-]{0,1}\\d{3}$";

    /**
     * Regex for city names. (ex: Warsaw)
     */
    public static final String CITY_REGEX = "^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$";

    /**
     * Regex for street name. (ex: Piekna)
     */
    public static final String STREET_REGEX = "[\\w\\s]+";

    /**
     * Regex for first name of a user (ex: Hanna)
     */
    public static final String FIRST_NAME_REGEX = "^[A-Za-z]+((\\s)?((\\'|\\-|\\.)?([A-Za-z])+))*$";

    /**
     * Regex for first name of a user (ex: Miklash)
     */
    public static final String LAST_NAME_REGEX = "([A-Z][a-zA-Z]*)";

    /**
     * Regex for the name for location/company/mechanics shop (ex: Anna)
     */
    public static final String NAME_REGEX = "^(?=\\s*\\S).*$";

    /**
     * Regex for car registration plate (ex: WY12345)
     */
    public static final String REGISTRATION_PLATE_REGEX = "^[A-Z]{2}\\d{5}$";

    /**
     * Regex for email address (ex: anna@email.com)
     */
    public static final String EMAIL_REGEX = "[A-Za-z0-9._%-+]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";


}
