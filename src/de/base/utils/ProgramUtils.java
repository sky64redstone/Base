package de.base.utils;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;

public class ProgramUtils {

    private ProgramUtils() {
    }

    public static void openFile(String path) {
        try {
            Desktop.getDesktop().open(new File(path));
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + " in de.base.utils.ProgramUtils.openFile");
        }
    }

    public static void openWebsite(String uri) {
        try {
            Desktop.getDesktop().browse(URI.create(uri));
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + " in de.base.utils.ProgramUtils.openWebsite");
        }
    }

    public static void editFile(String path) {
        try {
            Desktop.getDesktop().edit(new File(path));
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + " in de.base.utils.ProgramUtils.editFile");
        }
    }

    public static void mail() {
        try {
            Desktop.getDesktop().mail();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + " in de.base.utils.ProgramUtils.mail");
        }
    }

    public static void moveFileToTrash(String path) {
        Desktop.getDesktop().moveToTrash(new File(path));
    }

    public static void printFile(String path) {
        try {
            Desktop.getDesktop().print(new File(path));
        } catch (IOException e) {
            System.err.println(e.getClass().getName() + " in de.base.utils.ProgramUtils.printFile");
        }
    }

    public static boolean isConnectedWithServer(String serverURL) {
        if (!isConnectedWithInternet())
            return false;
        try {
            return Runtime.getRuntime().exec("ping " + serverURL).waitFor() == 0;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + " in de.base.utils.ProgramUtils.isConnectedWithServer");
        }
        return false;
    }

    public static boolean isConnectedWithInternet() {
        try {
            return Runtime.getRuntime().exec("ping www.google.de").waitFor() == 0;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + " in de.base.utils.ProgramUtils.isConnectedWithInternet");
        }
        return false;
    }

    public static void openExplorer(String path) {
        try {
            Runtime.getRuntime().exec("explorer.exe /select, " + path);
        } catch (IOException e) {
            System.err.println(e.getClass().getName() + " in de.base.utils.ProgramUtils.openExplorer");
        }
    }

    public static File chooseJFile() {
        return chooseJFile(null);
    }

    public static File chooseJFile(Component component) {
        return chooseJFile(component, null);
    }

    public static File chooseJFile(Component component, String approveButtonText) {
        JFileChooser chooser = new JFileChooser();
        chooser.showDialog(component, approveButtonText);
        return chooser.getSelectedFile();
    }
}
