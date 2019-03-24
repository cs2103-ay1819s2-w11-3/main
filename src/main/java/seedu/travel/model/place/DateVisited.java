package seedu.travel.model.place;

import static java.util.Objects.requireNonNull;

import static seedu.travel.commons.util.AppUtil.checkArgument;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents the date that the place was visited in TravelBuddy.
 * Guarantees: immutable; is valid as declared in {@link #isValidDateVisited(String)}
 */
public class DateVisited {

    public static final String MESSAGE_INCORRECT_FORMAT = "Date visited must follow the DD/MM/YYYY format";
    public static final String MESSAGE_FUTURE_DATE_ADDED = "Date visited must a present or past date";
    public final String date;

    /**
    * Constructs a {@code DateVisited}.
    *
    * @param strDateVisited A valid date visited.
    */
    public DateVisited(String strDateVisited) {
        requireNonNull(strDateVisited);
        checkArgument(isCorrectDateFormat(strDateVisited), MESSAGE_INCORRECT_FORMAT);
        checkArgument(isValidDateVisited(strDateVisited), MESSAGE_FUTURE_DATE_ADDED);
        date = strDateVisited;
    }

    /**
     * Returns true if a given string is in the correct date format.
     */
    public static boolean isCorrectDateFormat(String strDate) {
        if (!(strDate instanceof String)) {
            throw new java.lang.NullPointerException();
        }
        if (strDate.trim().equals("")) {
            return false;
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        simpleDateFormat.setLenient(false);
        Date date;

        // Checking if the format is correct.
        try {
            date = simpleDateFormat.parse(strDate);
        } catch (ParseException e) {
            // format is incorrect.
            return false;
        }
        return true;
    }

    /**
     * Returns true if a given string is a present or past date.
     * Assume that the person is born after the date 01/01/1900.
     */
    public static boolean isValidDateVisited(String strDate) {
        if (!(strDate instanceof String)) {
            throw new java.lang.NullPointerException();
        }
        Date todayDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dateVisit;

        try {
            dateVisit = simpleDateFormat.parse(strDate);
        } catch (ParseException e) {
            // format is incorrect.
            return false;
        }

        if (dateVisit.after(todayDate)) {
            return false;
        }

        String noPersonBornBeforeThisDate = "01/01/1900";
        Date birthDate;
        try {
            birthDate = simpleDateFormat.parse(noPersonBornBeforeThisDate);
        } catch (ParseException e) {
            // format is incorrect.
            return false;
        }

        if (todayDate.before(birthDate)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return date.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof DateVisited // instanceof handles nulls
            && date.equals(((DateVisited) other).date)); // state check
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }

}