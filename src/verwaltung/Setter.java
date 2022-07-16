package verwaltung;

import mediaDB.Uploader;

import java.math.BigDecimal;
import java.util.Date;

public interface Setter {
    void setSize(BigDecimal size);
    void setAddress(String address);
    void setUploader(Uploader uploader);
    void setUploadDate(Date uploadDate);

}
