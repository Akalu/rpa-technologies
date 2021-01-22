package com.automation;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.KeyModifier;
import org.sikuli.script.Screen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.automation.mapper.SourceMap;

import java.net.URISyntaxException;
import java.util.UUID;

import org.sikuli.basics.Settings;

public class WordAutomation {

    static Logger log = LoggerFactory.getLogger(CalculatorAutomation.class);

    private Screen screen;
    private SourceMap source;

    public WordAutomation() throws URISyntaxException {
        Settings.MinSimilarity = 0.8d;
        screen = new Screen();
        source = new SourceMap("word");
    }

    private void startTest() throws FindFailed, InterruptedException {
        clickWindowsStartAndOpenMSWord();
        typeTextInWordAndSave();
    }

    private void clickWindowsStartAndOpenMSWord() throws FindFailed {
        screen.click(source.target("windows-start-1.png"));
        screen.wait(1.0); // need delay to allow animation to bring start menu
        screen.type("word");
        screen.wait(1.0); // wait for 1 second to show results
        screen.type(Key.ENTER);
    }

    private void typeTextInWordAndSave() throws FindFailed, InterruptedException {
        screen.click(source.target("blank-document-1.png"));
        screen.type("This is a test demonstrating image-based automation. Bye-bye!");
        screen.type("s", KeyModifier.CTRL);
        screen.click(source.target("browse-save-1.png"));
        screen.type("test-document" + UUID.randomUUID().toString());
        screen.click(source.target("btn-save-1.png"));

        Thread.sleep(3000);
        // exit
        screen.keyDown(Key.ALT);
        screen.keyDown(Key.F4);
        screen.keyUp();
    }

    public static void main(String... args) throws FindFailed, URISyntaxException, InterruptedException {
        WordAutomation wordAutomation = new WordAutomation();
        wordAutomation.startTest();
        log.info("complete");
    }
}
