package mediaDB;

import verwaltung.Setter;
import verwaltung.Updatable;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

public class VideoImpl implements Video, Setter, Updatable, Serializable {
    static final long serialVersionUID=2L;
    private Date uploadDate;
    private Uploader uploader;
    private String address;
    private BigDecimal size;
    private long accessCount = 0;

    private String name;


    @Override
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String getName() {
        return name;
    }



    @Override
    public Collection<Tag> getTags() {
        return null;
    }
    @Override
    public BigDecimal getBitrate() {
        return null;
    }
    @Override
    public Duration getLength() {
        return null;
    }
    @Override
    public int getResolution() {
        return 0;
    }
    @Override
    public BigDecimal getSize() {
        return size;
    }
    @Override
    public Uploader getUploader() {
        return uploader;
    }
    @Override
    public Date getUploadDate() {
        return uploadDate;
    }


    @Override
    public long getAccessCount() {
        return accessCount;
    }
    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public void setSize(BigDecimal size) {
        this.size = size;
    }
    @Override
    public void setAddress(String address)                  {
        this.address = address;
    }
    @Override
    public void setUploader(Uploader uploader) {
        this.uploader = uploader;
    }
    @Override
    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }
    @Override
    public void update() {
        accessCount++;;
    }
}
