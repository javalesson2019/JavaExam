//dir — выводит список файлов в текущей директории

package exam;

import java.io.File;

import exam.CommandLine.Command;
import exam.CommandLine.Context;

class DirCommand implements Command {

	@Override
	public void printHelp() {
		System.out.println(getDescription());
	}

	@Override
	public boolean execute(Context context, String... args) {
		printDir(context.getCurrentDirectory());
		return true;
	}

	@Override
	public String getName() {
		return "DIR";
	}

	// TODO: код написан небрежно! Где форматирование?
		private void printDir(File dir) {
			  File[] files = dir.listFiles();
	           if (files != null) {
	               for (File f : files) {
	                   System.out.println(f.getName()); // TODO: где файл, а где папка?
	               }
	           }
	}

	@Override
	public String getDescription() {
		return "Prints directory content";
	}
}

