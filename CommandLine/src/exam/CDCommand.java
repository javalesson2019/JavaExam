// cd «путь» — перейти в директорию, путь к которой задан первым аргументом

package exam;

import java.io.File;

import exam.CommandLine.Command;
import exam.CommandLine.Context;

class CDCommand implements Command {

	@Override
	public void printHelp() {
		System.out.println(getDescription());
	}

	@Override
	public boolean execute(Context context, String... args) {
		doCD(context.currentDirectory, args);
		return true;
	}

	@Override
	public String getName() {
		return "CD";
	}

	private void doCD(File dir, String[] args) {
		if (args == null) {
			System.out.println("enter: cd + <name of the new directory>");
		} else {
			String change = args[0];
			System.out.println("Current working directory:");
			System.out.println(System.getProperty("user.dir"));

			System.out.println("Changing working directory...\n");
			System.setProperty("user.dir", change);

			System.out.println("Current working directory:");
			System.out.println(System.getProperty("user.dir"));

		}
	}

	@Override
	public String getDescription() {
		return "Changes directory";
	}
}
