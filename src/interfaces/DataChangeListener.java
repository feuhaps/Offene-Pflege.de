/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.EventListener;

/**
 *
 * @author tloehr
 */
public interface DataChangeListener<T> extends EventListener {

    void dataChanged(DataChangeEvent<T> evt);

}
