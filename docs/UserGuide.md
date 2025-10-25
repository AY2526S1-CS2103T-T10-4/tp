---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# CCAmper :fa-solid-campground: User Guide

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------
## Welcome to CCAmper!
CCAmper is the perfect app for **secondary school teachers** as an all-in-one desktop app for
- Managing all your various CCA student details plus emergency contact details
- Keeping track of critical information such as their dietary restrictions
- Keeping track of their attendance and performance.

CCAmper is optimized for use via a
<tooltip content="Command Line Interface">**CLI**</tooltip> to help secondary school teachers who are fast-typers to
**plan, manage, and simplify** your CCA's weekly tasks faster than traditional
<tooltip content="Graphical User Interface">GUI</tooltip> apps.


## Quick start

1. Download and install Java `17` or above from this
   <a href="https://www.oracle.com/java/technologies/downloads/#java17">Java downloads page</a>.<br>
   **Mac users:** Ensure you have the precise JDK version prescribed
   [here](https://se-education.org/guides/tutorials/javaInstallationMac.html).<br> {{ verifyJava17 }}

2. Download our latest CCAmper `.jar` file from our [GitHub page](https://github.com/AY2526S1-CS2103T-T10-4/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.
   <br>We recommend placing the `.jar` file into a folder named `CCAmper` on your desktop for easy access.

3. Open a command terminal, {{ navigate }} into the folder containing the `.jar` file,
   and use the `java -jar addressbook.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.

<br>
   <img src="images/Ui.png" width="600" height="450"/>
<br><br>

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * `delete 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

2. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

### Prerequisite knowledge

<box type="info" seamless>

**Notes about the syntax and command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in **square brackets** are optional.<br>
  * e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.
  * e.g `n/NAME [enroll/[YEAR]]` can be used as `n/John enroll/` or as `n/John enroll/2024` or as `n/John`.

* Items with `…` after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…` can be empty, `t/friend`, `t/friend family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</box>

<box type="important" seamless>

**Constraints on Person contacts**

| Field                 | Requirements                                                                                                                                                                                                                                                           |
|-----------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Name                  | • Letters, numbers, and spaces and the following `.,-'()` only.<br>• Cannot be blank                                                                                                                                                                                   |
| Phone                 | • Exactly 8 digits that starts with one of `3/6/8/9`.                                                                                                                                                                                                                  |
| Email                 | • Local-part contains only alphanumeric characters and the following `+_.-` <br/>• Domain label must consist of only alphanumeric characters and hyphens, <br/>and the end part (after the period) must be at least 2 characters long. <br/>(example.email@do-main.ca) |
| Address               | • Any characters, but cannot be blank.                                                                                                                                                                                                                                 |
| Year                  | • Positive integer, or blank to not add one.                                                                                                                                                                                                                           |
| Roles                 | • Contains only alphanumeric characters or spaces.                                                                                                                                                                                                                     |
| Tags                  | • Contains only alphanumeric characters (No spaces).                                                                                                                                                                                                                   |
| Emergency<br/>Contact | • Name and Phone of emergency contact has the same constraints as specified above.                                                                                                                                                                                     |
| Pin                   | • Input either `TRUE` or `FALSE` (Non case-sensitive)                                                                                                                                                                                                                  |

**Duplicate handling**
* We require that no two person have the exact same `Name`, `Phone` and `Email` combination.

</box>

<box type="tip" seamless>

**Style Guide**

* Additionally, we check user input if they follow the recommended style for the field.<br>
  These do not throw an error, but will display a warning message after the command finishes execution,<br>
  and during launch of the program.

| Field                 | Style Guide                                                                                               |
|-----------------------|:----------------------------------------------------------------------------------------------------------|
| Name                  | • Proper capitalization<br/>• No consecutive spaces<br/>• Brackets close, e.g. `John (Doe` is not stylish |
| Address               | • Proper capitalization                                                                                   |
| Roles                 | • Proper capitalization<br/>• No consecutive spaces                                                       |
| Emergency<br/>Contact | • Name field has the same recommendations as specified above.                                             |

</box>

### Successful Commands

Succesful commands should look like such:

<img src="images/successfulCommand.png" height="120" width="800"/>
<br><br>

### Invalid Commands
A warning message will be provided for invalid commands with text providing help on command usage:

<img src="images/invalidCommand.png" height="120" width="800"/>
<br><br>

### Viewing help : `help`

A popup appears which redirects the user to this current page. :D

<img src="images/helpMessage.png" alt="Help Message Window" height="80" width="650"/>
<br><br>

### Adding a person: `add`

Add students to your address book, with the given fields.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [r/ROLE]…​ [t/TAG]…​ [pin/] [enroll/YEAR] [ecn/EMERGENCY_NAME] [ecp/EMERGENCY_PHONE]`

Additional information on fields:
* While one may key in phone numbers with hyphen(s) and/or space(s) to improve human-readability, the hyphen(s) and space(s) are automatically removed when phone number is stored in app.
* The emergency contact fields (`ecn` and `ecp`) must be either all provided or not at all.

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 r/President r/Camp leader ecn/Jack Doe ecp/99998888 ece/jackd@example.com enroll/2022 t/friend`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/98765432 t/criminal enroll/2024`

<box type="tip">

**Tip:** A person can have any number of tags (including 0)
</box>

<br>

### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

It should look like this:

<img src="images/list.png" width="650" height="400"/>

<br><br>

### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [r/ROLE]…​ [t/TAG]…​ [pin/(TRUE/FALSE)] [enroll/[YEAR]] [ecn/EMERGENCY_NAME] [ecp/EMERGENCY_PHONE]`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without specifying any tags after it.
* Editing roles follows the same rules as editing tags.
* The pin field takes in either "TRUE" or "FALSE"
* The emergency contact fields (`ecn` and `ecp`) must be either both provided or not at all.
* The enrollment year should be a positive integer or empty string (to delete)

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.
*  `edit 3 n/Jack Wilson r/` Edits the name of the 3rd person to be `Jack Wilson` and clears all existing roles.
*  `edit 1 n/John p/98765432 e/johndoe@example.com a/New Place r/Senior Student t/experienced pin/TRUE enroll/2022 ecn/Sally ecp/88887777`

### Locating persons by fields: `find`

Display a list of persons satisfying the search constraint.

Format: `find [n/KEYWORD [MORE_KEYWORDS]] [t/KEYWORD [MORE_KEYWORDS]] [enroll/[(<\|<=\|>\|>=\|=)NUMBER]] [r/KEYWORD]…`

* At least one prefix must be provided.
* If different prefixes are provided, a list of persons matching ALL the given constraints are shown<br>
(except for groups of prefixes that are the same, which will be `OR` search).

| Search type                                     | Notes                                                                                                                                                                                                                                                                                                                                         |
|-------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Name (n/NAME...)                                | • Case-insensitive<br/>• Matches the full word, e.g. `Han` does not match `Hans`<br/>• If more than 1 keyword is specified, people with names matching at least one keyword is returned (`OR` search).<br/>e.g. `Hans Bo` returns `Bo Yang` and `Hans Gruber`                                                                                 |
| Tag (t/TAG...)                                  | • Case-insensitive<br/>• Matches substrings of the word. e.g. `friend` will match `friends`.<br/>• If more than 1 keyword is specified, people with tags matching any is returned. (`OR` search)                                                                                                                                              |
| Role (r/\[ROLE]) \[r/ROLE]...                   | • Case-insensitive<br/>• Matches substrings of the word.<br/>• Spaces are included in the search. <br/>e.g. `r/section leader` matches `Section Leader` but not `Section Head` and not `Leader Section`.<br/>• Leave empty to search for people having any role.<br/>• Multiple roles can be specified using multiple prefixes. (`OR` search) |
| Enrollment Year<br/>(enroll/\[OPERATOR + YEAR]) | • Must be either empty (Search for people with empty year field) <br/>or contain one of the following operators `<, <=, >, >=, =` and a positive integer.<br/>e.g. `<2025` returns people enrolled in `2024` or earlier.                                                                                                                      |

Examples:
* `find n/John` returns `john` and `John Doe`
* `find n/yu john enroll/>=2022` returns `John`, `Bernice Yu`<br>

<img src="images/findCommand.png" width="400" height="500"/>
<br><br>

* `find r/lead r/sec` returns `Arthur John`, `Carl Jun`, `Dominic Low`<br><br>
<img src="images/findCommand2.png" width="400" height="500"/>

<br><br>

### Deleting a person : `delete`

Deletes the person at the specified index. Cannot be undone.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the currently displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the displayed person list.
* `find n/Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

<br>

### Adding an event: `add:event`

Add events to your address book, with the given fields given there are no duplicate events.

Format: `add:event n/NAME d/(d/M/yyyy or d/M/yyyy-d/M/yyyy) [info/DESCRIPTION]`

Examples:
* `add:event n/meeting d/1/10/2025 info/routine meeting`

<box type="definition">

**Note:**
Events are considered duplicates if they have the same name and duration.
</box>

<br>

### Deleting an event : `delete:event`

Deletes the event at the specified index. Cannot be undone.

Format: `delete:event INDEX`

* Deletes the event at the specified `INDEX`.
* The index refers to the index number shown in the currently displayed event list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `delete:event 2` deletes the 2nd event in the displayed event list.

<br>

### Consolidate student info : `consolidate`

Consolidate students' a) names, b) phone number, c) email and d) address and
display data under corresponding categories.

Format: `consolidate`

* If there are repeated values for a particular category (e.g. two students share the same phone number),
  then that particular value (e.g. phone number) is displayed once.

<br>

### Clearing all contacts : `clear`

<box type="warning">

**Warning:** Deletes all contacts from the address book. Not undoable.
</box>

<br>

### Clearing all events : `clear:event`

<box type="warning">

**Warning:** Deletes all events from the address book. Not undoable.
</box>

<br>

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving of data

CCAmper data are saved in the hard disk automatically after any command that changes the data.
<br>There is no need to save manually.

<br>

### Editing the data file

AddressBook data are saved automatically as a JSON file `[JAR file location]/data/CCAmper.json`.
<br>Advanced users are welcome to update data directly by editing that data file.

<box type="warning">

**Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.  Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the AddressBook to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</box>

--------------------------------------------------------------------------------------------------------------------

## Planned updates

1. We are planning to integrate an **event list**, which can be used to keep track of weekly session attendance of students.

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

**Q**: I would like to report a bug / give feedback for the product. Where can I do so?<br>
**A**: Please raise an issue on our <a href="https://github.com/AY2526S1-CS2103T-T10-4/tp/issues">GitHub</a> page.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.
2. **If you minimize the Help Window** and then run the `help` command (or use the `Help` menu, or the keyboard shortcut `F1`) again, the original Help Window will remain minimized, and no new Help Window will appear. The remedy is to manually restore the minimized Help Window.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action     | Format, Examples
-----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------
**Help** | `help`
**Add**    | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [r/ROLE]…​ [t/TAG]…​ [pin/] [enroll/YEAR] [ecn/EMERGENCY_NAME] [ecp/EMERGENCY_PHONE]` <br> e.g., `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 r/president r/camp leader ecn/Jack Doe ecp/99998888 enroll/2022 t/friend`
**List** | `list`
**Edit** | `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [r/ROLE]…​ [t/TAG]…​ [pin/(TRUE/FALSE)] [enroll/[YEAR]] [ecn/EMERGENCY_NAME] [ecp/EMERGENCY_PHONE]`<br> e.g.,`edit 1 n/John p/98765432 e/johndoe@example.com a/New Place r/Senior Student t/experienced pin/TRUE enroll/2022 ecn/Sally ecp/88887777`
**Find** | `find [n/KEYWORD [MORE_KEYWORDS]] [t/KEYWORD [MORE_KEYWORDS]] [enroll/[(<\|<=\|>\|>=\|=)NUMBER]] [r/SUBSTRING]…​`<br> e.g., `find n/yu john enroll/>=2022 r/lead r/sec`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Add Event** | `add:event n/NAME d/(d/M/yyyy or d/M/yyyy-d/M/yyyy) [info/DESCRIPTION]` <br> e.g., `add:event n/meeting d/1/10/2025 info/routine meeting`
**Delete Event** | `delete:event INDEX`<br> e.g., `delete:event 3`
**Consolidate** | `consolidate`
**Clear**  | `clear`
**Clear Events**  | `clear:event`
