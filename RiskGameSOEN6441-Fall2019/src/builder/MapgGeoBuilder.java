package builder;

import model.MapGeo;

abstract public class MapgGeoBuilder {
	 private MapGeo mapGeo;
	 public MapGeo getMapGeo() {
		 return mapGeo;
	 }
	 public void setMapGeo(MapGeo mapGeo)
	 {
		 this.mapGeo=mapGeo;
	 }
abstract public void saveMapView() throws Exception;
abstract public void savePlayers() throws Exception;

}
