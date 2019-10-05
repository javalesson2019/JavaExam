// pwd — вывести полный путь до текущей директории

package exam;

import java.io.File;

import exam.CommandLine.Command;
import exam.CommandLine.Context;

class PWDCommand implements Command {

	@Override
	public void printHelp() {
		System.out.println(getDescription());
	}

	@Override
	public boolean execute(Context context, String... args) {
		System.out.println(doPWD(context.getCurrentDirectory()));
		return true;
	}

	public String doPWD(File dir) {
		String s = "";
		try {
			System.out.println(dir.getAbsolutePath().toString());	
		} catch (Error e) {
			s = "Default directory is not an absolute path\n";
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