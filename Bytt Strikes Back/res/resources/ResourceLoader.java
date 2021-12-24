package resources;
import java.awt.*;
import java.net.URL;

public class ResourceLoader {
	
	static ResourceLoader rl=new ResourceLoader();
	
	public static URL getImage(String Filename){
		//return Toolkit.getDefaultToolkit().getImage(rl.getClass().getResource("images/"+Filename));
		return rl.getClass().getResource("images/"+Filename);
	}

}
