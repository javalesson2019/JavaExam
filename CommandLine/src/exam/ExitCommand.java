package exam;

import exam.CommandLine.Command;
import exam.CommandLine.Context;

class ExitCommand implements Command {
	@Override
	public boolean execute(Context context, String... args) {
	
		System.out.println("Finishing command processor... done.");
		return false;
	}

	@Override
	public void printHelp() {
		System.out.println(getDescription());
	}

	@Override
	public String getName() {
		return "EXIT";
	}

	@Override
	public String getDescription() {
		return "Exits from command processor";
	}
}