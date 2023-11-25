package My_Functional_Interface;

import javax.swing.*;
import java.awt.*;

/**
 * This functional interface is mainly used for testing
 * @author Jerry
 */
@FunctionalInterface
public interface OptionPane {
    /**
     * This method is for overriding by lambda expression to show a confirm dialog
     * @param parentComponent It indicates the parent Component of the target Component
     * @param message It indicates the message that is printed on the Dialog
     * @param title It indicates the title of the Dialog
     * @param optionType It indicates the type of option (e.g. Yes, No or OK and so on)
     * @param messageType It indicates the type of message (e.g. error message)
     * @return A constant integer of the button information which is clicked by user
     */
    int showConfirmDialog(Component parentComponent, Object message, String title, int optionType, int messageType);
}