// pwd — вывести полный путь до текущей директории

package exam;

import java.nio.file.Path;
import java.nio.file.Paths;

import exam.CommandLine.Command;
import exam.CommandLine.Context;

class PWDCommand implements Command {

	@Override
	public void printHelp() {
		System.out.println(getDescription());
	}

	@Override
	public boolean execute(Context context, String... args) {
		System.out.println(doPWD());
		return true;
	}

	public String doPWD() {
		String s = "";
		try {
			Path currentRelativePath = Paths.get("");
			System.out.println(System.getProperty("user.dir"));
		} catch (Error e) {
			s= "Default directory is not an absolute path\n";
		}
		
		return s;
	}
	@Override
	public String getName() {
		return "PWD";
	}

	@Override
	public String getDescription() {
		return "Shows full path to current directory";
	}
}