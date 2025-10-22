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

<box type="info" seamless>

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in **square brackets** are optional.<br>
  * e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.
  * e.g `n/NAME [enroll/[YEAR]]` can be used as `n/John enroll/` or as `n/John enroll/2024` or as `n/John`.
    
* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
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

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [r/ROLE]…​ [t/TAG]…​ [pin/] [enroll/YEAR] [ecn/EMERGENCY_NAME] [ecp/EMERGENCY_PHONE] [ece/EMERGENCY_EMAIL]`

Additional information on fields:
* While one may key in phone numbers with hyphen(s) and/or space(s) to improve human-readability, the hyphen(s) and space(s) are automatically removed when phone number is stored in app.
* The emergency contact fields (`ecn, ecp, ece`) must be either all provided or not at all.

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 r/president r/camp leader ecn/Jack Doe ecp/99998888 ece/jackd@example.com enroll/2022 t/friend`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/98765432 t/criminal enroll/2024`

<box type="tip">

**Tip:** A person can have any number of tags (including 0)
</box>

<box type="definition">

**Note:**
Persons are considered duplicates if they have the same name
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

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [r/ROLE]…​ [t/TAG]…​ [pin/(TRUE/FALSE)] [enroll/[YEAR]] [ecn/EMERGENCY_NAME] [ecp/EMERGENCY_PHONE] [ece/EMERGENCY_EMAIL]`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.
* Editing roles follows the same rules as editing tags.
* The pin field takes in either "TRUE" or "FALSE"
* The emergency contact fields (`ecn, ecp, ece`) must be either all provided or not at all.
* The enrollment year should be a positive integer or empty string (to delete)

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.
*  `edit 3 n/Jack Wilson r/` Edits the name of the 3rd person to be `Jack Wilson` and clears all existing roles.
*  `edit 1 n/John p/98765432 e/johndoe@example.com a/New Place r/Senior Student t/experienced pin/TRUE enroll/2022 ecn/Sally ecp/88887777 ece/sally@example2.com`

### Locating persons by fields: `find`

Display a list of persons satisfying the search constraint.

Format: `find [n/KEYWORD [MORE_KEYWORDS]] [t/KEYWORD [MORE_KEYWORDS]] [enroll/[(<\|<=\|>\|>=\|=)NUMBER]]`

Available prefixes
- Name: `n/` (Name containing any of the keywords)
- Enrollment Year: `enroll/` (Enrollment year satisfy constraints)
- Tag: `t/` (Any tag containing any of the keywords)

Format: `find [n/KEYWORD [MORE_KEYWORDS]] [t/KEYWORD [MORE_KEYWORDS]] [enroll/[(<\|<=\|>\|>=\|=)NUMBER]] 
[r/SUBSTRING]…​`

* At least one prefix must be provided.
* If different prefixes are provided, a list of persons matching ALL the given constraints are shown.

Name:
* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Tag:
* The search is case-insensitive. e.g. `friends` will match `Friends`
* A tag matches a search keyword if the keyword is a substring of the tag. e.g. `friend` will match `my friends`.
* A person is matched and returned if any of their tags matches any of the searched keywords.

Enrollment Year:
* An operator and an integer needs to be provided (in that order).
* The operator must be one of `<, <=, >, >=, =`.
* The integer must be non-negative.
* For e.g. `<2025` will return contacts that enrolled in `2024` or earlier.
* A empty string can also be provided to find contacts without an enrollment year

Role:
* The search is case-insensitive. e.g. `president` will match `President`.
* A role matches a search string if the string is a substring of the role. e.g. `section` will match `Section Leader`.
* Unlike previous cases, we can search for a string with spaces e.g. `r/section leader` will match `Section Leader` 
  but not `Section Head`.
* To search for multiple roles, we supply the `r/` tag multiple times e.g. `r/section r/leader` matches both 
  `Section Head` and `Group Leader`.
* A person is matched and returned if any of their roles matches any of the searched strings.
* We can match all people with roles by giving an empty substring e.g. `find r/`.

Examples:
* `find n/John` returns `john` and `John Doe`
* `find n/yu john enroll/>=2022` returns `John`, `Bernice Yu`<br>

<img src="images/findCommand.png" width="400" height="500"/>

* `find r/lead r/sec` returns `Arthur John`, `Carl Jun`, `Dominic Low`<br>
<img src="images/findCommand2.png" width="400" height="500"/>

<br><br>

### Deleting a person : `delete`

Deletes the person at the current index. Cannot be undone.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the currently displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find n/Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

<br>

### Clearing all entries : `clear`

<box type="warning">

**Warning:** Deletes all entries from the address book. Not undoable.
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
**Add**    | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [r/ROLE]…​ [t/TAG]…​ [pin/] [enroll/YEAR] [ecn/EMERGENCY_NAME] [ecp/EMERGENCY_PHONE] [ece/EMERGENCY_EMAIL]` <br> e.g., `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 r/president r/camp leader ecn/Jack Doe ecp/99998888 ece/jackd@example.com enroll/2022 t/friend`
**Clear**  | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [r/ROLE]…​ [t/TAG]…​ [pin/(TRUE/FALSE)] [enroll/[YEAR]] [ecn/EMERGENCY_NAME] [ecp/EMERGENCY_PHONE] [ece/EMERGENCY_EMAIL]`<br> e.g.,`edit 1 n/John p/98765432 e/johndoe@example.com a/New Place r/Senior Student t/experienced pin/TRUE enroll/2022 ecn/Sally ecp/88887777 ece/sally@example2.com`
**Find** | `find [n/KEYWORD [MORE_KEYWORDS]] [t/KEYWORD [MORE_KEYWORDS]] [enroll/[(<\|<=\|>\|>=\|=)NUMBER]] [r/SUBSTRING]…​`<br> e.g., `find n/yu john enroll/>=2022 r/lead r/sec`
**List** | `list`
**Help** | `help`