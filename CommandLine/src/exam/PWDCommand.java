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
		try {
			Path currentRelativePath = Paths.get("");
			String s = currentRelativePath.toAbsolutePath().toString();
			System.out.println(s);
		} catch (Error e) {
			System.err.println("Default directory is not an absolute path\n");
		}
		return true;
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