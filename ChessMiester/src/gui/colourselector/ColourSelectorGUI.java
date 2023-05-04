/**
 * This class is the GUI implementation for the main menu.
 *
 * @author Zach Eanes (50%), Kaushal Patel (50%)
 */
package gui.colourselector;

import enums.ToScreen;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import gui.colourselector.components.SliderPane;

public class ColourSelectorGUI extends GridPane implements SliderChangeListener {

    /** The selected colour as a hex value (RGB) **/
    String selectedColor;

    /** Red colour slider panel **/
    private SliderPane red;

    /** Green colour slider panel **/
    private SliderPane green;

    /** Blue colour slider panel **/
    private SliderPane blue;

    /** Confirms a colour choice **/
    private Button selectButton;

    /** Cancels a colour choice **/
    private Button exitButton;

    /** Area on to which the colour is shown **/
    private StackPane colour;

    /** The colour text label **/
    private Label hexColour;

    /** The title label **/
    private Label titleLabel;

    /** Maximum Colour Intensity **/
    private final int MAX_INTENSITY = 255;

    /** Minimum Colour Intensity **/
    private final int MIN_INTENSITY = 0;

    /** The Colour Selector pane. */
    GridPane colourSelectorPane;

    /** Scene for the main menu. */
    private Scene scene;

    /** Integer values for the RGB **/
    int redColour, greenColour, blueColour;


    public ColourSelectorGUI(){
        // Initialize the RGB values to 0
        this.redColour = 0;
        this.greenColour = 0;
        this.blueColour = 0;

        // Create a border pane
        this.colourSelectorPane = new GridPane();

        // Vertical Spacing for GridPane
        this.colourSelectorPane.setVgap(20);

        // Grid constraints for row
        RowConstraints row0 = new RowConstraints();
        RowConstraints row1 = new RowConstraints();
        RowConstraints row2 = new RowConstraints();
        RowConstraints row3 = new RowConstraints();

        row0.setPercentHeight(10);
        row1.setPercentHeight(40);
        row2.setPercentHeight(40);
        row3.setPercentHeight(10);

        // Grid constraints for column
        ColumnConstraints col0 = new ColumnConstraints();
        ColumnConstraints col1 = new ColumnConstraints();

        col0.setPercentWidth(50);
        col1.setPercentWidth(50);

        // Apply constraints
        this.colourSelectorPane.getRowConstraints().addAll(row0,row1,row2);
        this.colourSelectorPane.getColumnConstraints().addAll(col0,col1);

        // Top label for title
        this.titleLabel = new Label("Select Color");

        // Position Title
        this.titleLabel.setAlignment(Pos.CENTER);
        GridPane.setHalignment(this.titleLabel, HPos.CENTER);

        // Top Panel for Colour
        this.colour = new StackPane();
        colour.getStylesheets().add("colour");

        this.hexColour = new Label();
        this.hexColour.getStylesheets().add("colour_text");

        colour.getChildren().add(this.hexColour);
        this.setBackground(0,0,0);   // Set to black

        // Create Panel for Sliders with Centered Layout
        HBox sliders = new HBox();
        sliders.setSpacing(20.0);
        sliders.setAlignment(Pos.CENTER);

        // Construct 3 sliders for RGB
        this.red = new SliderPane("Red", MIN_INTENSITY, MIN_INTENSITY, MAX_INTENSITY);
        this.green = new SliderPane("Green", MIN_INTENSITY, MIN_INTENSITY, MAX_INTENSITY);
        this.blue = new SliderPane("Blue", MIN_INTENSITY, MIN_INTENSITY, MAX_INTENSITY);

        // Register this class as the listener for the sliders
        this.red.setListener(this);
        this.green.setListener(this);
        this.blue.setListener(this);

        // Add the sliders to the hbox
        sliders.getChildren().addAll(this.red, this.green, this.blue);

        // Bottom buttons
        this.selectButton = new Button("Select");
        this.exitButton = new Button("Exit");

        // Button Sizes
        this.selectButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        this.exitButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        // Button Event Handlers
        this.selectButton.setOnAction(buttonHandler);
        this.exitButton.setOnAction(buttonHandler);

        // Slider and Colour sizes
        sliders.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        this.colour.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        // Add the components to the pane
        this.colourSelectorPane.add(this.titleLabel, 0, 0, 2, 1);
        this.colourSelectorPane.add(this.colour, 0, 1, 2, 1);
        this.colourSelectorPane.add(sliders, 0, 2, 2, 1);
        this.colourSelectorPane.add(this.selectButton, 0, 4, 1, 1);
        this.colourSelectorPane.add(this.exitButton, 1, 4, 1, 1);

        // Get stylesheet
        this.colourSelectorPane.getStylesheets().add(
                getClass().getResource("ColourSelector.css").toExternalForm());
    }
    /**
     * Getter for the scene.
     * @return the scene
     */
    public Pane getRoot(){ return this.colourSelectorPane; }

    /**
     * Set the background of the chooser
     * @param r The red intensity 0..255
     * @param g The green intensity 0..255
     * @param b The blue intensity 0..255
     */
    public void setBackground(int r, int g, int b){
        // Invalid value check
        if (r > 255 || g > 255 || b > 255) return;

        String redHex = Integer.toHexString(r);
        String greenHex = Integer.toHexString(g);
        String blueHex = Integer.toHexString(b);

        if (r + g + b / 3 > 127){
            this.hexColour.setTextFill(Color.BLACK);
        }
        else{
            this.hexColour.setTextFill(Color.WHITE);
        }

        // Add preceeding 0 if only 1 char
        if (redHex.length() == 1)
            redHex = 0 + redHex;
        if (greenHex.length() == 1)
            greenHex = 0 + greenHex;
        if (blueHex.length() == 1)
            blueHex = 0 + blueHex;

        this.selectedColor = redHex + greenHex + blueHex;

        this.colour.setStyle("-fx-background-color: #" + this.selectedColor);

        this.hexColour.setText("#" + this.selectedColor);

    }

    /**
     * Handler for when a slider is changed
     * @param sliderPane The slider pane with the changed value
     * @param newValue The new value of the slider pane
     */
    @Override
    public void sliderChanged(SliderPane sliderPane, int newValue) {
        if (sliderPane == red){
            this.redColour = newValue;
        }
        else if (sliderPane == green){
            this.greenColour = newValue;
        }
        else if (sliderPane == blue){
            this.blueColour = newValue;
        }
        setBackground(this.redColour, this.greenColour, this.blueColour);
    }

    EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
            Object source = event.getSource();

            if (source == selectButton){
                System.out.println("Save button pressed");
            } else if (source == exitButton) {
                System.out.println("Exit button pressed");
            }
        }
    };
}
