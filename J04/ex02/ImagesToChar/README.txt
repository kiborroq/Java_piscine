Console program. One prints the image to console using specified colors.
JDBC and JCommander external libraries were used for this project.
JCDP - is a Java library that offers a convenient way to print colored messages or debug messages on a terminal.
JCommander - is a very small Java framework that makes it trivial to parse command line parameters (https://jcommander.org/).

For project building: javac -d target -cp src/lib/JCDP-4.0.2.jar:src/lib/jcommander-1.81.jar src/java/edu/school21/printer/*/*.java && cp -r src/resources/ src/lib/*  target/

For jar building:  jar -vcmf src/manifest.txt target/images-to-chars-printer.jar -C target/ edu -C target/ resources/

For project run:  java -jar target/images-to-chars-printer.jar --white="color" --black="color"
Where: --white="an color name"
       --black="an color name"
Expected one of the next colors: RED, BLACK, WHITE, CYAN, GREEN, YELLOW, BLUE, MAGENTA, "".

