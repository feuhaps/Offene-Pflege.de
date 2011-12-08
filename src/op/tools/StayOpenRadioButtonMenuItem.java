package op.tools;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * http://www.camick.com/java/source/StayOpenRadioButtonMenuItem.java
 */
public class StayOpenRadioButtonMenuItem extends JRadioButtonMenuItem {

    private static MenuElement[] path;

    {
        getModel().addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                if (getModel().isArmed() && isShowing()) {
                    path = MenuSelectionManager.defaultManager().getSelectedPath();
                }
            }
        });
    }

    /**
     * @see JRadioButtonMenuItem#JRadioButtonMenuItem()
     */
    public StayOpenRadioButtonMenuItem() {
        super();
    }

    /**
     * @see JRadioButtonMenuItem#JRadioButtonMenuItem(Action)
     */
    public StayOpenRadioButtonMenuItem(Action a) {
        super();
    }

    /**
     * @see JRadioButtonMenuItem#JRadioButtonMenuItem(Icon)
     */
    public StayOpenRadioButtonMenuItem(Icon icon) {
        super(icon);
    }

    /**
     * @see JRadioButtonMenuItem#JRadioButtonMenuItem(Icon, boolean)
     */
    public StayOpenRadioButtonMenuItem(Icon icon, boolean selected) {
        super(icon, selected);
    }

    /**
     * @see JRadioButtonMenuItem#JRadioButtonMenuItem(String)
     */
    public StayOpenRadioButtonMenuItem(String text) {
        super(text);
    }

    /**
     * @see JRadioButtonMenuItem#JRadioButtonMenuItem(String, boolean)
     */
    public StayOpenRadioButtonMenuItem(String text, boolean selected) {
        super(text, selected);
    }

    /**
     * @see JRadioButtonMenuItem#JRadioButtonMenuItem(String, Icon)
     */
    public StayOpenRadioButtonMenuItem(String text, Icon icon) {
        super(text, icon);
    }

    /**
     * @see JRadioButtonMenuItem#JRadioButtonMenuItem(String, Icon, boolean)
     */
    public StayOpenRadioButtonMenuItem(String text, Icon icon, boolean selected) {
        super(text, icon, selected);
    }

    /**
     * Overridden to reopen the menu.
     *
     * @param pressTime the time to "hold down" the button, in milliseconds
     */
    @Override
    public void doClick(int pressTime) {
        super.doClick(pressTime);
        MenuSelectionManager.defaultManager().setSelectedPath(path);
    }

    @Override
    public Point getMousePosition(boolean allowChildren) throws HeadlessException {
        return super.getMousePosition(allowChildren);    //To change body of overridden methods use File | Settings | File Templates.
    }
}