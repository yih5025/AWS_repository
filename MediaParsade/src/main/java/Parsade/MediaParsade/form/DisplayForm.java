package Parsade.MediaParsade.form;


import lombok.Data;

@Data
public class DisplayForm {

    private String type;
    private String text;
    private String result;

    public DisplayForm() {

    }

    public DisplayForm(String type, String text, String result) {
        this.type = type;
        this.text = text;
        this.result = result;
    }
}
