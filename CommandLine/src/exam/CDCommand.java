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
			doCD(context, args);
		return true;
	}

	@Override
	public String getName() {
		return "CD";
	}

	private void doCD(Context dir, String[] args) {
		if (args == null) {
			System.out.println("enter: cd + <name of the new directory>");
		} else {

			File name = new File(args[0]);
			dir.setCurrentDirectory(name);
			System.out.println("Changing working directory...\n");
			
		}
	}

	@Override
	public String getDescription() {
		return "Changes directory";
	}
}
