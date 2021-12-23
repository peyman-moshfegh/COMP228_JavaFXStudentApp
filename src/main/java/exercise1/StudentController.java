package exercise1;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class StudentController {

    public String[] csCourses = {"python", "Java", "C#"};
    public String[] bsCourses = {"Accounting", "Finance", "Management"};
    public ArrayList<String> studentCourses = new ArrayList<String>();

    public ComboBox<String> courses;
    public RadioButton cs;
    public RadioButton bs;
    public TextArea listBox;

    public CheckBox studentCouncil;
    public CheckBox volunteerWork;

    public TextField name;
    public TextField address;
    public TextField province;
    public TextField city;
    public TextField postalCode;
    public TextField phoneNumber;
    public TextField email;

    public Button display;
    public TextArea information;

    public boolean csSelected = true;
    public boolean bsSelected = false;

    public void populate(MouseEvent mouseEvent) {
        if ((mouseEvent.getSource() == cs && bsSelected) ||
                (mouseEvent.getSource() == bs && csSelected)) {
            studentCourses.clear();
            listBox.clear();
        }
        if (cs.isSelected()) {
            courses.setItems(FXCollections.observableArrayList(csCourses));
            csSelected = true;
            bsSelected = false;
        }
        if (bs.isSelected()) {
            courses.setItems(FXCollections.observableArrayList(bsCourses));
            csSelected = false;
            bsSelected = true;
        }
    }

    public void getCourses(ActionEvent actionEvent) {
        String s = courses.getValue();
        if (s != null && !studentCourses.contains(s)) {
            studentCourses.add(s);
            if (listBox.getText().equals("Required"))
                    listBox.clear();
            listBox.appendText(s + "\n");
        }
    }

    public void displayInfo(MouseEvent mouseEvent) {
        if (emptyCheck(name, address, province, city, postalCode, phoneNumber, email, listBox))
            return;
        String s = String.format("%s, %s, %s, %s, %s, %s, %s.%n%n",
                name.getText(), address.getText(), province.getText(), city.getText(),
                postalCode.getText(), phoneNumber.getText(), email.getText());
        s += "Program:\n" + (cs.isSelected() ? "Computer Science" : "Business");
        s += "\n\nCourses:\n" + listBox.getText() + "\n";
        if (studentCouncil.isSelected())
            s += "Student is a member of student council.\n";
        if (volunteerWork.isSelected())
            s += "Student has done volunteer work.";
        information.clear();
        information.appendText(s);
    }

    public boolean emptyCheck(TextInputControl ...textIC) {
        for (TextInputControl t: textIC) {
            if (t.getText().equals("") || t.getText().equals("Required")) {
                t.clear();
                t.appendText("Required");
                information.clear();
                return true;
            }
        }
        return false;
    }
}