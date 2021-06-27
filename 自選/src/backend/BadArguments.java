package backend;

/**
 * This is exception Class that will be triggered when you use getter with unexpected argument.<br>
 * There for the getter cannot find the correct value in json file.<br>
 * @author kobemary
 *
 */
public class BadArguments extends RuntimeException {
	public BadArguments(String msg) {
		super("Bad arguments: " + msg);
		
	}

	public BadArguments() {
	}
	public String getMessage() {
		return super.getMessage();
		
	}

	public static void main(String[] args) {
		Ticket t = new Ticket();
		// correct arguments

		System.out.print(t.getTicketInfo(0, "date"));

		System.out.print(t.getTicketInfo(0, "data"));

	}

}
