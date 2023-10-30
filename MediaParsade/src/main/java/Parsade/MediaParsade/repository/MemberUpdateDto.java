package Parsade.MediaParsade.repository;


import lombok.Data;

@Data
public class MemberUpdateDto {


    private String type;
    private String text;
    private String selection;

    public MemberUpdateDto() {
    }

    public MemberUpdateDto(String type, String text, String selection) {
        this.type = type;
        this.text = text;
        this.selection = selection;
    }
}
