package ClasseBase;

import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;

public class DatePickerCell extends DateCell {

    private final LocalDate minDate;

    // Constructor for DatePickerCell without a minimum date restriction
    public DatePickerCell() {
        this.minDate = null;
    }

    // Constructor for DatePickerCell with a minimum date restriction
    public DatePickerCell(LocalDate minDate) {
        this.minDate = minDate;
    }

    @Override
    public void updateItem(LocalDate item, boolean empty) {
        super.updateItem(item, empty);

        // Disable past dates
        if (item.isBefore(LocalDate.now())) {
            setDisable(true);
            setStyle("-fx-background-color: #ffc0cb;"); // Disable past dates' background color
        }

        // Disable dates before the minimum date
        if (minDate != null && item.isBefore(minDate)) {
            setDisable(true);
            setStyle("-fx-background-color: #ffc0cb;"); // Disable dates before the minimum date's background color
        }
    }
}
