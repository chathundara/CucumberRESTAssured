package pojo;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonFromLink {

	public static void main(String args[]) throws JSONException, MalformedURLException, IOException {
		JSONObject json = new JSONObject(IOUtils.toString(new URL("https://tools.learningcontainer.com/sample-json.json"), Charset.forName("UTF-8")));

System.out.println(json.toString());
//System.out.println(System.getProperty("os.name").toUpperCase());
	}
}