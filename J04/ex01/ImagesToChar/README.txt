Console program that prints an image to console using specified chars.

For project building: javac -d target/ src/java/edu/school21/printer/*/*.java && cp -r src/resources target/

For jar building: jar -cvmf src/manifest.txt target/images-to-chars-printer.jar -C target/ edu/ -C target/ resources/

For project run:  java -jar target/images-to-chars-printer.jar --white="char" --black="char"
Where: --white="char for white color replacing"
       --black="char for black color replacing"

