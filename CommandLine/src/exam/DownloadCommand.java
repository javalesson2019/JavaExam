//Команда download «url» «имя_файла» - загружает файл

package exam;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import exam.CommandLine.Command;
import exam.CommandLine.Context;

public class DownloadCommand implements Command {
	@Override
	public void printHelp() {
		System.out.println(getDescription());
	}

	@Override
	public boolean execute(Context context, String... args) {
	
		download(context.getCurrentDirectory(), args);
		
		return true;
	}

	@Override
	public String getName() {
		return "DOWNLOAD";
	}

	private void download(File dir, String[] args) {
		if (args == null) {
			System.out.println("enter: download + <url> + <fileName>");
		} else {
			// url пример
			// https://docs.oracle.com/javase/7/docs/api/java/nio/channels/FileChannel.html
			String url = args[0];
			if (args.length == 1) {
				System.out.println("enter: download + <url> + <fileName>\n" + "enter name after url!");
			} else {
				String fileName = args[1];
				URL website;
				try {
					website = new URL(url);
					ReadableByteChannel rbc = Channels.newChannel(website.openStream());
					FileOutputStream fos = new FileOutputStream(fileName);
					fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
					System.out.println(fileName + " was succesfully downloaded");
					fos.close();
				} catch (Exception e) {
					System.err.println("something went wrong...");
				}
			}
		}

	}

	@Override
	public String getDescription() {
		return "Downloads a file";
	}
}
