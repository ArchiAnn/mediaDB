package Handler;

public class HandlerImpl implements Handler{
    private int option;
    @Override
    public void getOption(int option) {
        this.option = option;
    }

}
