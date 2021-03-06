package seedu.travel.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.travel.commons.core.Messages;
import seedu.travel.logic.CommandHistory;
import seedu.travel.model.Model;
import seedu.travel.model.place.RatingContainsKeywordsPredicate;

/**
 * Finds and lists all places in travel book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class SearchRatingCommand extends Command {

    public static final String COMMAND_WORD = "searchr";
    public static final String COMMAND_ALIAS = "sr";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all places whose ratings contain "
            + "the specified ratings and displays them as a list with index numbers.\n"
            + "Parameters: RATING [MORE_RATINGS]...\n"
            + "Example: " + COMMAND_WORD + " 4 5";

    private final RatingContainsKeywordsPredicate predicate;

    public SearchRatingCommand(RatingContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.updateFilteredPlaceList(predicate);
        return new CommandResult(constructFeedbackToUser(model));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SearchRatingCommand // instanceof handles nulls
                && predicate.equals(((SearchRatingCommand) other).predicate)); // state check
    }

    /**
     * Constructs the message to be displayed upon successful SearchRatingCommand
     * @param model Model component
     * @return A string showing a successful search that will be displayed on the GUI
     */
    private String constructFeedbackToUser(Model model) {
        StringBuilder feedbackToUser = new StringBuilder();
        feedbackToUser.append(COMMAND_WORD);
        feedbackToUser.append(" ");
        feedbackToUser.append(predicate.getKeywords());
        feedbackToUser.append(": ");
        feedbackToUser.append(String.format(Messages.MESSAGE_PLACES_LISTED_OVERVIEW,
                model.getFilteredPlaceList().size()));
        return feedbackToUser.toString();
    }
}
