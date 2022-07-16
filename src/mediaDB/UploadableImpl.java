package mediaDB;

import java.util.Date;

public class UploadableImpl implements Uploadable{
    private Date uploadDate;
    private Uploader uploader;
    private String name;


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setUploader(Uploader uploader) {
        this.uploader = uploader;
    }
    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }
    @Override
    public Uploader getUploader() {
        return uploader;
    }
    @Override
    public Date getUploadDate() {
        return uploadDate;
    }
}
