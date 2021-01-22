package com.automation;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.automation.geometry.Rectangle;
import com.automation.mapper.SourceMap;

import java.io.IOException;
import java.net.URISyntaxException;
import org.sikuli.basics.Settings;

public class CalculatorAutomation {

    static Logger log = LoggerFactory.getLogger(CalculatorAutomation.class);

    private Screen screen;
    private SourceMap source;

    public CalculatorAutomation() throws URISyntaxException {
        Settings.MinSimilarity = 0.7d;
        Settings.OcrTextRead = true;
        screen = new Screen();
        source = new SourceMap("calculator");
    }

    private void startTest() throws FindFailed, InterruptedException {
        clickWindowsStartAndOpenMSWord();
        typeTextInWordAndSave();
    }

    private void clickWindowsStartAndOpenMSWord() throws FindFailed {
        screen.click(source.target("windows-start-1.png"));
        screen.wait(1.0); // need delay to allow animation to bring start menu
        screen.type("calculator");
        screen.wait(1.0); // wait for 1 second to show results
        screen.type(Key.ENTER);
    }

    private void typeTextInWordAndSave() throws FindFailed, InterruptedException {
        Thread.sleep(2000);
        // calibration
        screen.keyDown(Key.ALT);
        screen.keyDown(Key.SPACE);
        screen.keyDown("x");
        screen.keyUp();

        Thread.sleep(2000);
        log.info("check non-existing elem");
        Match match = screen.exists(source.target("navigation-btn-1.png"), 15.0);
        if (match == null) {
            throw new FindFailed("calc-type-1.pngnot found");
        }
        screen.click(source.target("navigation-btn-1.png"));
        Thread.sleep(2000);
        screen.click(source.target("calc-type-1.png"));
        Thread.sleep(2000);
        screen.click(source.target("four-btn-1.png"));
        screen.click(source.target("six-btn-1.png"));
        screen.click(source.target("eight-btn-1.png"));
        screen.click(source.target("log-btn-1.png"));
        Thread.sleep(1000);
        
        Rectangle rect = new Rectangle(5, (int) (0.12 * screen.h), (int) (0.80 * screen.w), (int) (0.14 * screen.h));
        log.info("rect =", rect);

        Region regResult = screen.newRegion(rect.getX(), rect.getY(), rect.getW(), rect.getH());

        String txt = regResult.text();
        log.info("result= {}", txt);
        log.info("result={}", regResult.toString());
        regResult.highlight(2);
        Thread.sleep(1000);
        // exit
        screen.keyDown(Key.ALT);
        screen.keyDown(Key.F4);
        screen.keyUp();
    }

    private Region locateWindow(int topX, int topY, int bottomX, int bottomY) {
        return screen;

    }

    private static void addCreds(String ip, String userName, String password) throws IOException {
        Process p = Runtime.getRuntime().exec("cmdkey /generic:" + ip + " /user:" + userName + " /pass:" + password);
        p.destroy();
    }

    private static void delCreds(String ip) throws IOException {
        Process p = Runtime.getRuntime().exec("cmdkey /delete:" + ip);
        p.destroy();
    }

    private static void rdpConn(String ip) throws IOException {
        Runtime.getRuntime().exec("mstsc /v: " + ip + " /f");
    }

    public static void main(String... args) throws FindFailed, URISyntaxException, InterruptedException, IOException {

        Thread.sleep(5000);
        CalculatorAutomation sikuliAutomation = new CalculatorAutomation();
        sikuliAutomation.startTest();
        log.info("complete");
    }
}
