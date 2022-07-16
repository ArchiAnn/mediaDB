package mediaDB;

import java.util.Collection;

public interface Content {
    String getAddress();
    Collection<Tag> getTags();
    long getAccessCount();
}
