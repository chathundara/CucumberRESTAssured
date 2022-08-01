package resources;

public enum  APIresources {
	//enum is special class in java which has collection of constants or methods
	
	AddPlaceAPI("/maps/api/place/add/json"),
	getPlaceAPI("/maps/api/place/get/json"),
	DeletePlaceAPI("/maps/api/place/delete/json");
	private String resources;
	
	APIresources(String resource) {
		this.resources=resource;
	}
	
	public String getResources() {
		return resources;
	}

}
