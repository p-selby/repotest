import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.apache.tools.ant.launch.Launcher;

public class ResourceTest {
	public static void main( String args[] ) {
		try {
			// obtain a class loader reference
			ClassLoader	ld = ClassLoader.getSystemClassLoader();
			// copy build.xml to /tmp
			InputStream	is = ld.getResourceAsStream( "build.xml" );
			BufferedReader	br = new BufferedReader( new InputStreamReader( is ) );
			PrintWriter	pw = new PrintWriter( new FileWriter( "/tmp/build.xml" ) );
			String		lineIn = null;
			while( ( lineIn = br.readLine() ) != null )
				pw.println( lineIn );
			br.close();
			pw.flush();
			pw.close();
			// start ant
			String	antArgs[] = { "-f", "/tmp/build.xml" };
			Launcher.main( antArgs );
		}
		catch( Exception e ) {
			e.printStackTrace();
		}
	}
}
