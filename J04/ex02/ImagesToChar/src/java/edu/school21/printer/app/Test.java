package edu.school21.printer.app;

import com.beust.jcommander.JCommander;

public class Test {

		public static void main(String[] args) {
			ParseArgument pa = new ParseArgument();
			JCommander.newBuilder().addObject(pa).build().parse(args);
			System.out.println(pa.getWhite());
			System.out.println(pa.getPath());
			System.out.println(pa.getBlack());
		}
}



//public class Test {
//
//		public static void main(String[] args) {
//
//			//example of a terminal Printer
//			Printer p = new Printer.Builder(Printer.Types.TERM).build();
//			p.println(p);
//			p.println("This is a normal message.");
//			p.errorPrintln("This is an error message.");
//			p.debugPrintln("This debug message is always printed.");
//			p = new Printer.Builder(Printer.Types.TERM).level(1).timestamping(false).build();
//			p.println(p);
//			p.debugPrintln("This is printed because its level is <= 1", 1);
//			p.debugPrintln("This isn't printed because its level is > 1", 2);
//			p.setLevel(2);
//			p.debugPrintln("Now this is printed because its level is <= 2", 2);
//
//			//example of a Colored terminal Printer (WINDOWS or UNIX)
//			ColoredPrinter cp = new ColoredPrinter();
//			cp.println(cp);
//			cp.setAttribute(Ansi.Attribute.REVERSE);
//			cp.println("This is a normal message (with format reversed).");
//			//reseting the terminal to its default colors
//			//temporarily overriding that format
//			cp.print("\tMADE ", Ansi.Attribute.BOLD, Ansi.FColor.YELLOW, Ansi.BColor.GREEN);
//			cp.print("IN PORTUGAL\t\n", Ansi.Attribute.BOLD, Ansi.FColor.YELLOW, Ansi.BColor.RED);
//			cp.println("I hope you find it useful ;)");
//
//		}
//}
