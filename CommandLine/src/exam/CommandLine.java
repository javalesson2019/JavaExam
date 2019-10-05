package exam;

import java.io.File;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class CommandLine {
	Map<String, Command> commands;

	String consoleEncoding;

	public CommandLine(String consoleEncoding) {
		commands = new TreeMap<>();
		Command cmd = new HelpCommand();
		commands.put(cmd.getName(), cmd);
		cmd = new DirCommand();
		commands.put(cmd.getName(), cmd);
		cmd = new CDCommand();
		commands.put(cmd.getName(), cmd);
		cmd = new PWDCommand();
		commands.put(cmd.getName(), cmd);
		cmd = new ExitCommand();
		commands.put(cmd.getName(), cmd);
		cmd = new CATCommand();
		commands.put(cmd.getName(), cmd);
		cmd = new DownloadCommand();
		commands.put(cmd.getName(), cmd);
		this.consoleEncoding = consoleEncoding;
	}

	public void execute() {
		Context c = new Context();
		c.currentDirectory = new File(".").getAbsoluteFile();
		
		boolean result = true;
		Scanner scanner = new Scanner(System.in, consoleEncoding);
		do {
			System.out.print("> ");
			String fullCommand = scanner.nextLine();
			ParsedCommand pc = new ParsedCommand(fullCommand);
			if (pc.command == null || "".equals(pc.command)) {
				continue;
			}
			Command cmd = commands.get(pc.command.toUpperCase());
			if (cmd == null) {
				System.out.println("Command not found");
				continue;
			}
			result = cmd.execute(c, pc.args);
		} while (result);
		scanner.close();
	}

	public static void main(String[] args) {
		CommandLine cp = new CommandLine("Cp1251");
		cp.execute();
	}

	class ParsedCommand {

		String command;

		String[] args;

		public ParsedCommand(String line) {
			String parts[] = line.split(" ");
			if (parts != null) {
				command = parts[0];
				if (parts.length > 1) {
					args = new String[parts.length - 1];
					System.arraycopy(parts, 1, args, 0, args.length);
				}
			}
		}
	}

	interface Command {

		boolean execute(Context context, String... args);
		
		void printHelp();

		String getName();

		String getDescription();
	}

	class Context {
		private File currentDirectory;

		public File getCurrentDirectory() {
			return currentDirectory;
		}

		public void setCurrentDirectory(File currentDirectory) {
			this.currentDirectory = currentDirectory;
		}
		
	}

	class HelpCommand implements Command {
		@Override
		public boolean execute(Context context, String... args) {
			if (args == null) {
				System.out.println("Avaliable commands:\n");
				for (Command cmd : commands.values()) {
					System.out.println(cmd.getName() + ": " + cmd.getDescription());
				}

			} else {
				for (String cmd : args) {
					System.out.println("Help for command " + cmd + ":\n");
					Command command = commands.get(cmd.toUpperCase());
					if (command == null) {
						System.out.println("Command not found");
					} else {
						command.printHelp();
					}

				}
			}
			return true;
		}

		@Override
		public void printHelp() {
			System.out.println(getDescription());
		}

		@Override
		public String getName() {
			return "HELP";
		}

		@Override
		public String getDescription() {
			return "Prints list of available commands\n"
					+ "   ->[ help + <nameOfCommand> prints what the command does ]";
		}
	}

}
