package classeBase;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;

import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;

public class DatePickerCell extends DateCell {
    private final LocalDate debutDatePickerValue;

    public DatePickerCell() {
        this.debutDatePickerValue = null;
    }

    public DatePickerCell(LocalDate debutDatePickerValue) {
        this.debutDatePickerValue = debutDatePickerValue;
    }

    @Override
    public void updateItem(LocalDate item, boolean empty) {
        super.updateItem(item, empty);

        if (item.isBefore(LocalDate.now())) {
            setDisable(true);
        } else if (debutDatePickerValue != null && item.isBefore(debutDatePickerValue.plusDays(1))) {
            setDisable(true);
        }
    }
}