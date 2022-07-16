package mediaDB;

import java.util.Date;

public interface Uploadable {
    Uploader getUploader();
    Date getUploadDate();

    void setName(String name);
    String getName();
}
