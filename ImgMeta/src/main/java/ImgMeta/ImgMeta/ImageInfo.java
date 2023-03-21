package ImgMeta.ImgMeta;
import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.lang.GeoLocation;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.GpsDirectory;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ImageInfo {

    private File image;
    private Metadata metadata;
    
    public ImageInfo(File image)
            throws ImageProcessingException, IOException {
        this.image = image;
        metadata = ImageMetadataReader.readMetadata(image);
    }

    public Map<String, String> getMapInfo() {
        Map<String, String> map = new HashMap<String, String>();
        for (Directory d : metadata.getDirectories()) {
            for (Tag tag : d.getTags()) {
                map.put(tag.getTagName(), tag.getDescription());
            }
        }
        return map;
    }

    public Optional<GeoLocation> getCoordinates() {
        Collection<GpsDirectory> gpsDirectories =
                metadata.getDirectoriesOfType(GpsDirectory.class);
        for (GpsDirectory gpsDirectory : gpsDirectories) {
            GeoLocation geoLocation = gpsDirectory.getGeoLocation();
            if (geoLocation != null && !geoLocation.isZero()) {
                return Optional.of(geoLocation);
            }
        }
        return Optional.empty();
    }
}