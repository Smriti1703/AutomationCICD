package data;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import com.fasterxml.jackson.core.json.async.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;  
public class DataReader {
	
	@SuppressWarnings("deprecation")
	public List<HashMap<String, String>> getJsonDataToMap() throws IOException
	{
		//read json to string
		String jsonContent =   FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\java\\data\\PurchaseOrder.json"),StandardCharsets.UTF_8);
	
	//convert string to hashmap
	ObjectMapper mapper =new ObjectMapper();
	List<HashMap<String,String>> data =mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){
			});
			return data;
	}

}