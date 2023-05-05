package gui.colourselector;

import gui.colourselector.components.SliderPane;

public interface SliderChangeListener {

    /**
     * Called when a slider is changed
     * @param sliderPane The slider pane with the changed value
     * @param newValue The new value of the slider pane
     */
    public void sliderChanged(SliderPane sliderPane, int newValue);
}
