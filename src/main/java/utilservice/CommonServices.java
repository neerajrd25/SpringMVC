package utilservice;

import java.util.Date;

public class CommonServices {

	public CommonServices() {
		System.out.println(getClass() + " Getting Initiliazed : " + this.hashCode());
	}

	public String getCurrentTime() {
		System.out.println("Example by Autowiring");
		return new Date().toString();
	}
}
