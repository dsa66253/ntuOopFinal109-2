package backend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.json.*;
import java.net.*;

/**
 * This is used to get terperature of a certain city.
 * Usually, you don't need to create instance of Crawler. Just use Static function.
 * @author kobemary
 *
 */
public class Crawler {
	/**
	 * @param _locationName String type to indicate which location weather you want to know
	 * @return String type of temperature in _locationName
	 * @throws IOException
	 */
	public static String weather(String _locationName) throws IOException{
		String dataid="F-C0032-001";
		String apikey="CWB-0F2980DC-85D7-42D3-8CAE-B60552697285";
		String format="JSON";
		String locationName=URLEncoder.encode(_locationName, "UTF-8");
		URL url = new URL("https://opendata.cwb.gov.tw/api/v1/rest/datastore/"+dataid+"?Authorization="+apikey+"&locationName="+locationName);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		int status = con.getResponseCode();
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
		    content.append(inputLine);
		}
		in.close();
		con.disconnect();
		JSONObject response = new JSONObject(content.toString());
//		System.out.println(response);
		JSONObject taipei = response.getJSONObject("records").getJSONArray("location").getJSONObject(0);
		String MaxT = taipei.getJSONArray("weatherElement").getJSONObject(4).getJSONArray("time").getJSONObject(0).getJSONObject("parameter").getString("parameterName");
		String MinT = taipei.getJSONArray("weatherElement").getJSONObject(2).getJSONArray("time").getJSONObject(0).getJSONObject("parameter").getString("parameterName");
//		System.out.println(MaxT);
//		System.out.println(MinT);
		String mean = String.valueOf( (Integer.parseInt(MaxT)+ Integer.parseInt(MinT))/2 );
		return mean;
	}
	/**
	 * @return String type of temperature in Taipei city
	 * @throws IOException
	 */
	public static String weather() throws IOException{
		String dataid="F-C0032-001";
		String apikey="CWB-0F2980DC-85D7-42D3-8CAE-B60552697285";
		String format="JSON";
		String locationName=URLEncoder.encode("臺北市", "UTF-8");
		URL url = new URL("https://opendata.cwb.gov.tw/api/v1/rest/datastore/"+dataid+"?Authorization="+apikey+"&locationName="+locationName);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		int status = con.getResponseCode();
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
		    content.append(inputLine);
		}
		in.close();
		con.disconnect();
		JSONObject response = new JSONObject(content.toString());
//		System.out.println(response);
		JSONObject taipei = response.getJSONObject("records").getJSONArray("location").getJSONObject(0);
		String MaxT = taipei.getJSONArray("weatherElement").getJSONObject(4).getJSONArray("time").getJSONObject(0).getJSONObject("parameter").getString("parameterName");
		String MinT = taipei.getJSONArray("weatherElement").getJSONObject(2).getJSONArray("time").getJSONObject(0).getJSONObject("parameter").getString("parameterName");
//		System.out.println(MaxT);
//		System.out.println(MinT);
		String mean = String.valueOf( (Integer.parseInt(MaxT)+ Integer.parseInt(MinT))/2 );
		return mean;
	}



	
	
	
	
	public static void main(String[] args) throws IOException {
		System.out.println(Crawler.weather());//不輸入的話預設是“臺”北市
		System.out.println(Crawler.weather("屏東縣"));
		
		
		
//		String dataid="F-C0032-001";
//		String apikey="CWB-0F2980DC-85D7-42D3-8CAE-B60552697285";
//		String format="JSON";
//		String locationName=URLEncoder.encode("臺北市", "UTF-8");
//		URL url = new URL("https://opendata.cwb.gov.tw/api/v1/rest/datastore/"+dataid+"?Authorization="+apikey+"&locationName="+locationName);
//		HttpURLConnection con = (HttpURLConnection) url.openConnection();
//		con.setRequestMethod("GET");
//		int status = con.getResponseCode();
//		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//		String inputLine;
//		StringBuffer content = new StringBuffer();
//		while ((inputLine = in.readLine()) != null) {
//		    content.append(inputLine);
//		}
//		in.close();
//		con.disconnect();
//		JSONObject response = new JSONObject(content.toString());
//		System.out.println(response);
//		JSONObject taipei = response.getJSONObject("records").getJSONArray("location").getJSONObject(0);
//		String MaxT = taipei.getJSONArray("weatherElement").getJSONObject(4).getJSONArray("time").getJSONObject(0).getJSONObject("parameter").getString("parameterName");
//		String MinT = taipei.getJSONArray("weatherElement").getJSONObject(2).getJSONArray("time").getJSONObject(0).getJSONObject("parameter").getString("parameterName");
//		System.out.println(MaxT);
//		System.out.println(MinT);

	}

//	public static void main(String[] args) throws IOException {
//		// TODO Auto-generated method stub
////		Runtime rt = Runtime.getRuntime();
//////		Process pr = rt.exec("python");
//////		String command = "python crawler.py";
////		String command = "echo hello";
////		Process p = Runtime.getRuntime().exec(command);
////		Runtime.getRuntime().exec("echo hello");
//		Process process = Runtime.getRuntime().exec("./script.sh");
//		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//		String line;
//		while ((line = reader.readLine()) != null) {
//			System.out.println(line);
//		}
////		System.out.println(line);
////		int returnValue = process.waitFor();
////		System.out.println(String.format("Return: %d", returnValue));
//	}

}
