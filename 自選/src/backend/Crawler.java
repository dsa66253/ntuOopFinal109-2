package backend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.json.*;
import java.net.*;

public class Crawler {
	public static String weather(String _locationName) throws IOException{
		String dataid="F-C0032-001";
		String apikey="CWB-0F2980DC-85D7-42D3-8CAE-B60552697285";
		String format="JSON";
		String locationName="臺北市";
		URL url = new URL("https://opendata.cwb.gov.tw/fileapi/v1/opendataapi/"+dataid+"?Authorization="+apikey+"&Name="+locationName+"&format="+format);
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
		System.out.println(response);
		JSONObject taipei = response.getJSONObject("cwbopendata").getJSONObject("dataset").getJSONArray("location").getJSONObject(0);
		String MaxT = taipei.getJSONArray("weatherElement").getJSONObject(1).getJSONArray("time").getJSONObject(0).getJSONObject("parameter").getString("parameterName");
		String MinT = taipei.getJSONArray("weatherElement").getJSONObject(2).getJSONArray("time").getJSONObject(0).getJSONObject("parameter").getString("parameterName");
		System.out.println(MaxT);
		System.out.println(MinT);
		return null;
	}



	
	
	
	
	public static void main(String[] args) throws IOException {
		String dataid="F-C0032-001";
		String apikey="CWB-0F2980DC-85D7-42D3-8CAE-B60552697285";
		String format="JSON";
		String locationName="%E8%87%BA%E5%8C%97%E5%B8%82";
		URL url = new URL("https://opendata.cwb.gov.tw/api/v1/rest/datastore/F-C0032-001?Authorization=CWB-0F2980DC-85D7-42D3-8CAE-B60552697285&locationName=%E8%87%BA%E5%8C%97%E5%B8%82");
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
		System.out.println(response);
		JSONObject taipei = response.getJSONObject("cwbopendata").getJSONObject("dataset").getJSONArray("location").getJSONObject(0);
		String MaxT = taipei.getJSONArray("weatherElement").getJSONObject(1).getJSONArray("time").getJSONObject(0).getJSONObject("parameter").getString("parameterName");
		String MinT = taipei.getJSONArray("weatherElement").getJSONObject(2).getJSONArray("time").getJSONObject(0).getJSONObject("parameter").getString("parameterName");
		System.out.println(MaxT);
		System.out.println(MinT);

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
