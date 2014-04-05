package end2end;

import com.objogate.wl.Prober;
import com.objogate.wl.swing.AWTEventQueueProber;
import com.objogate.wl.swing.ComponentSelector;
import com.objogate.wl.swing.driver.JFrameDriver;
import com.objogate.wl.swing.driver.JButtonDriver;
import com.objogate.wl.swing.gesture.GesturePerformer;
import stockmanager.ui.MainWindow;

import javax.swing.*;

public class ApplicationDriver  extends JFrameDriver {

    public ApplicationDriver(int timeoutMillis) {

        super(new GesturePerformer(),
                JFrameDriver.topLevelFrame(
                        named(MainWindow.MAIN_WINDOW_NAME),
                        showingOnScreen()),
                new AWTEventQueueProber(timeoutMillis, 100));
    }


    private JButtonDriver calculateButton() {
        return new JButtonDriver(this, JButton.class, named(MainWindow.CALCULATE_BUTTON_NAME));
    }
}
