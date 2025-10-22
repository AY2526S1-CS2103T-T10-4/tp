<variable name="example">
To inject this HTML segment in your markbind files, use {{ example }} where you want to place it.
More generally, surround the segment's id with double curly braces.
</variable>

<br><br>

<!-- Shows a tooltip for instructions on navigating in the terminal. -->
<variable name="navigate">
<popover header="Basic commands" content="
`ls` to list and view all available files in the current folder.<br>
`cd ..` to navigate to a parent folder.<br>
`cd FILE_NAME` to navigate into FILE_NAME.<br>
">navigate
</popover> 
</variable>

<!-- Shows a popup for instructions on verifying the installed Java version. -->
<variable name="verifyJava17">
<trigger trigger="click" for="verify-java-17">
    How do I check that it's installed properly?
</trigger>

<modal id="verify-java-17" header="Verify installation of Java 17" center large>

1. On **Windows**, Press `Win + R`, type `cmd` and press Enter.<br>
   On **Mac**, Open `Terminal` from Applications.<br>
2. Type `java --version` and press Enter. <br>
3. The message should show Java 17 installed.

<img src="images/verify-java-17.png" alt="Image showing Java 17 installed" width="750" height="100"/>
</modal>
</variable>
