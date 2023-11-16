package My_Functional_Interface;

import javax.swing.*;
import java.awt.*;

public interface OptionPane {
    /**
     *  @see JOptionPane#showConfirmDialog(Component, Object, String, int, int);
     */
    int showConfirmDialog(Component parentComponent, Object message, String title, int optionType, int messageType);
}