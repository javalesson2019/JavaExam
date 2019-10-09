// cat «имя_файла» - выводит содержимое текстового файла «имя_файла»

package exam;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import exam.CommandLine.Command;
import exam.CommandLine.Context;

// TODO: Зачем дублировать суффикс Command? Имя класса не соответсвует правилами кодирования
class CATCommand implements Command {

	@Override
	public void printHelp() {
		System.out.println(getDescription());
	}

	@Override
	public boolean execute(Context context, String... args) {
		System.out.println(printStringTxt(args));
		return true;
	}

	@Override
	public String getName() {
		return "CAT";
	}

	private String printStringTxt(String... args) {
		StringBuilder content = new StringBuilder();
		if (args == null) {

		} else {
			String fileName = args[0];
			File file = new File(fileName);
			if (!file.exists()) {
				content.append("there is no file with this name");
			}

			if (file.exists() && file.length() > 0) {
				try {
					InputStream in = new FileInputStream(file.getPath());
					Scanner scanner = new Scanner(in);
					try {
						while (true) {
							content.append(scanner.nextLine()).append("\n");
						}
					} catch (NoSuchElementException e2) {
						System.out.print("");
					}

					in.close();
					scanner.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			return content.toString();
		}
		return "enter: cat <fileName>";
	}

	@Override
	public String getDescription() {
		return "Shows content of .txt file";
	}
}
