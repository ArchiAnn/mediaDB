package mediaDB;

import java.io.Serializable;

public class UploaderImpl implements Uploader, Serializable {
    static final long serialVersionUID=3L;
    private String name;

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String getName() {
        return name;
    }
}
