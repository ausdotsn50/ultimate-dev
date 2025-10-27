/*
    - Click button
    - Activate parser
    - Place parsed data into chosen DS
    - Pick item via randomizer
    - Check DS if empty or not
*/
package events.button;
import com.moandjiezana.toml.Toml;
import ud_interfaces.Play;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class GoToCategBehavior implements ButtonBehavior {
    @Override
    public void onClick(JButton button) {
        Play.categorySelect = false;

        String[] buttonName = button.getText().split(" ");
        String file = buttonName[0].substring(0, 1).toLowerCase() + buttonName[0].substring(1);
        System.out.println(file);

        // Pass the toml file to Play page, because Play page accesses DesignQuiz page
        Play.toml = new Toml().read(getTomlFile(file));

        // Access the parent component of the 'button clicked'
        Container parent = button.getParent();
        Play playScreen = null;

        // Loop up the component tree until we find the 'Play' panel
        while (parent != null) {
            // Instance of checker
            if (parent instanceof Play) {
                playScreen = (Play) parent;
                break;
            }
            parent = parent.getParent();
        }

        // Calling the refresh method that was 'generated'
        if (playScreen != null) { playScreen.refreshCenter(); }
        // Checker in case the component hierarchy changes
        else { System.err.println("Error: Could not find Play panel to refresh."); }
    }

    private Toml getTomlFile(String file) {
        try (InputStream input = getClass().getResourceAsStream("/questions/" + file + ".toml")) {
            // Checker if InputStream is null
            if (input == null) {
                throw new FileNotFoundException("TOML file not found");
            }

            // Read the TOML file using toml4j
            return new Toml().read(input);
        } catch (Exception e) {
            // Add more robust line
            return null;
        }
    }
}
