package events.button;

import com.moandjiezana.toml.Toml;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class GoToCategBehavior implements ButtonBehavior {
    @Override
    public void onClick(JButton button) {
        String[] buttonName = button.getText().split(" ");
        String file = buttonName[0].substring(0, 1).toLowerCase() + buttonName[0].substring(1);
        System.out.println(file);

        Toml qDotTOML = new Toml().read(getTomlFile(file));
        /*
            - Click button
            - Activate parser
            - Place parsed data into chosen DS
            - Pick item via randomizer
            - Check DS if empty or not
         */
        // return toml
        List<Map<String, Object>> questions = qDotTOML.getList("questions");

        for (Map<String, Object> q : questions) {
            System.out.println("Question: " + q.get("question"));
            System.out.println("Answer: " + q.get("answer"));
            System.out.println("Alternatives: " + q.get("alternatives"));
            System.out.println("--------------------------------------");
        }
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
