package ImgMeta.ImgMeta;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import com.drew.lang.GeoLocation;

public class App {
	//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
	public static void main(String[] args) {
		
		try {
			ImageInfo ii = new ImageInfo(new File("C:\\Tess4J\\469253_3349524100602_802427793_o.JPG"));//Path immagine
			Map<String, String> map = ii.getMapInfo();
						
			for (String name: map.keySet()) {
			    String key = name.toString();
			    String value = map.get(name).toString(); 
			    System.out.println(key +":" + value);
			}
			
			Optional<GeoLocation> geoLocation = ii.getCoordinates();
			
			double lat;
			double lon;
			if (geoLocation.isPresent()) {
				GeoLocation gl = geoLocation.get();
				lat = gl.getLatitude();
				lon = gl.getLongitude();
//				lblMap.setText("Apri mappa");
//				lblMap.setVisible(true);
			} else {
				lat = 0;
				lon = 0;
//				lblMap.setVisible(false);
			}
		} catch (Exception e) {
			//GenericDialog.showDialog(e.getMessage(), Alert.AlertType.ERROR);
		}

	}

}
