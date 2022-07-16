package GUI;

public class SimpleModel {
    private String text;
    public SimpleModel(){this.text="default";}
    public void setText(String text){this.text=text;}
    public String getText(){return this.text;}
    public int getLength(){return this.text.length();}
}
