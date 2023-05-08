package gui.colourselector;

import gui.colourselector.components.SliderPane;

/**
 * Interface for listening to slider changes
 *
 * @author Kaushal Patel (100%)
 * @version 1.0 (done in sprint 3)
 */
public interface SliderChangeListener {

    /**
     * Called when a slider is changed
     *
     * @param sliderPane The slider pane with the changed value
     * @param newValue The new value of the slider pane
     */
    public void sliderChanged(SliderPane sliderPane, int newValue);
}
