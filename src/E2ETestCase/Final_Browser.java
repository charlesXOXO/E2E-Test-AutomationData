package E2ETestCase;
import java.io.IOException;

import org.testng.annotations.Test;
import Resources.Base;

public class Final_Browser extends Base{

	@Test
	public void intialize() throws IOException {
		driver = intializeDriver();
		String CNAME = prop.getProperty("FinalURL");
		driver.get(CNAME);
	}
}
