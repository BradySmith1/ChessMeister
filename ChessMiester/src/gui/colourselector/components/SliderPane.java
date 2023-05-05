package gui.colourselector.components;

import gui.colourselector.SliderChangeListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class SliderPane extends VBox{

    /** The label of the control **/
    private Label title;

    /** The title above the value text field **/
    private Label valueTitle;

    /** The value of the control **/
    private TextField value;

    /** The slider of the control **/
    private Slider slider;

    /** The minimum value of the slider **/
    private int min;

    /** The maximum value of the slider **/
    private int max;

    /** The slider change lister **/
    public SliderChangeListener sliderListener;

    /**
     * Constructor for the slider pane
     * @param titleText The title of the control
     * @param initialValue The initial value of the control
     * @param min The minimum value of the slider
     * @param max The maximum value of the slider
     */
    public SliderPane(String titleText, int initialValue, int min, int max){
        this.min = min; // Set the minimum value
        this.max = max; // Set the maximum value

        this.setAlignment(Pos.CENTER); // Center the contents of the pane
        this.setSpacing(10.0); // Set the spacing between the contents of the pane

        // Create child components
        this.title = new Label(titleText);
        this.valueTitle = new Label("Value");
        this.value = new TextField(initialValue+"");
        this.slider = new Slider();
        this.slider.setOrientation(Orientation.VERTICAL);

        // Add the child components to the pane
        this.getChildren().addAll(this.title, this.valueTitle, this.value, this.slider);

        // Apply CSS Styles
        this.title.getStyleClass().add("slider_title");
        this.valueTitle.getStyleClass().add("slider_value_title");
        this.value.getStyleClass().add("slider_value");

        // Set up slider
        this.slider.setMin(this.min);
        this.slider.setMax(this.max);
        this.slider.setShowTickLabels(true);
        this.slider.setShowTickMarks(true);
        this.slider.setMajorTickUnit(max);
        this.slider.setMinorTickCount(5);
        this.slider.setBlockIncrement(10);

        this.slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue,
                    Number oldValue, Number newValue) {
                if(!oldValue.equals(newValue))
                    set(newValue.intValue());
            }
        });

        this.value.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue,
                    String oldValue, String newValue) {
                if(!oldValue.equals(newValue)){
                    try{
                        int valueToSet = Integer.parseInt(newValue);
                        set(valueToSet);
                        notifyObserver(valueToSet);
                    }
                    catch (NumberFormatException ignored){

                    }
                }
            }
        });
    }

    /**
     * Sets the value of the slider
     * @param newValue
     */
    public void set(int newValue){
        this.value.setText(newValue+"");
        this.slider.setValue(newValue);
    }

    /**
     * Gets the value of the slider
     * @return The value of the slider
     */
    public int getValue() {
        return (int) this.slider.getValue();
    }

    public void setListener(SliderChangeListener scl){
        this.sliderListener = scl;
    }

    public void notifyObserver(int newValue){
        this.sliderListener.sliderChanged(this, newValue);
    }
}
