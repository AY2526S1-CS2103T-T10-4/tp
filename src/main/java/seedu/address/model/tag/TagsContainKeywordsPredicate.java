package seedu.address.model.tag;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code tags} contains any of the keywords given.
 */
public class TagsContainKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public TagsContainKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return person.getTags().stream().anyMatch(this::tagContainsKeywords);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TagsContainKeywordsPredicate)) {
            return false;
        }

        TagsContainKeywordsPredicate otherTagsContainsKeywordsPredicate = (TagsContainKeywordsPredicate) other;
        return keywords.equals(otherTagsContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }

    /**
     * Returns true if the tag contains any of the keywords as a substring.
     */
    private boolean tagContainsKeywords(Tag tag) {
        return keywords.stream().anyMatch(keyword -> StringUtil.containsSubstringIgnoreCase(tag.tagName, keyword));
    }
}
