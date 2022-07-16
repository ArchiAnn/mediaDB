package mediaDB;

import verwaltung.Setter;
import verwaltung.Updatable;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

public class AudioImpl implements Audio, Setter, Updatable, Serializable {
    static final long serialVersionUID=1L;
    private Date uploadDate;
    private Uploader uploader;
    private String address;
    private BigDecimal size;
    private long accessCount = 0;

    public void increaseAccessCount(){
        accessCount++;
    }
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
    public int getSamplingRate() {
        return 0;
    }
    @Override
    public String getAddress() {
        return address;
    }
    @Override
    public Collection<Tag> getTags() {
        return null;
    }
    @Override
    public long getAccessCount() {
        return accessCount;
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
    public void update() {
        increaseAccessCount();
    }
}
