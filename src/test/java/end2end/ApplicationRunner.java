package end2end;
import stockmanager.Main;
import stockmanager.ui.MainWindow;

public class ApplicationRunner {

    private ApplicationDriver driver;
    public void start() {
        Thread thread = new Thread("TestProduce Application") {
            @Override public void run() {
                try {
                    Main.main(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread.setDaemon(true);
        thread.start();

        driver = new ApplicationDriver(1000);
        driver.hasTitle(MainWindow.APPLICATION_TITLE);
    }

    public void showsPriority() {
    }

    public void calculate() {

    }
}
