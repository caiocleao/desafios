package devPackeage;

public class CaioFormater {
	

public String formatText ( String text, int limit, Boolean justify ) {
		
		String formatedString = "";
		
		String[] lines = text.split("\n");
		for (int i = 0; i < lines.length; i++) {
			formatedString += formatLine( lines[i], limit, justify );
		}
				
		return formatedString;
	}
	
	// Words are breaking.
	public String formatLine ( String line, int limit, Boolean justify ) {
		
		int lineSize = line.length();
		int numberOfNecessaryLines;
			numberOfNecessaryLines = ( lineSize/limit ) + 1;
		
		String[] brokenLines= new String[numberOfNecessaryLines];
		String formatedLine = "";
		String currentLine = "";
		int beginIndex = 0;
		int endIndex = limit;
		int debug = 0;
		String lastLine = "";
		
		if ( line.length() == 0 ) {
			return "\n";
		} else {
		  
			for (int i = 0; i < brokenLines.length; i++) {
				
				if ( endIndex >= lineSize ) {
					endIndex = lineSize-1;
					
				} else if ( line.charAt(endIndex) != ' ' && line.charAt(endIndex) != '\n') {
					
					do {
						endIndex--;
						debug++;
					} while ( line.charAt(endIndex) != ' ' && line.charAt(endIndex) != '\n');
					
				
					if( i == brokenLines.length-1 ) {
						lastLine = line.substring(endIndex+1,line.length());
					}
					
				}
				
				
				brokenLines[i] = line.substring(beginIndex, endIndex);
				currentLine = line.substring(beginIndex, endIndex+1);
				if (justify) { 
					currentLine = justifyLine(currentLine, limit);
				}
				
				formatedLine += currentLine + "\n";
				beginIndex = endIndex+1;
				endIndex +=limit;
				
				/* If the last line size is bigger than the number of characters
				 we had to skip not to break a word, the size of the array will not fit.
				 So we manually add the last line in case our begin index is smaller than
				 the allowed line length */
				if( i == brokenLines.length-1 && beginIndex < line.length() ) {
					lastLine = line.substring(beginIndex,line.length());
				}
				
			}
			
		}
		
		System.out.println(debug);
		return formatedLine+lastLine;
		
	}
	
	public String justifyLine ( String line, int limit ) {

		// If last character is a space, remove it.
		if ( line.charAt(line.length()-1) == ' ' ) {
			line = line.substring(0, line.length()-1);
		}
		// Remove all breaklines, as they count on the limit lenght. They are added back after the value is returned.
		line = line.replaceAll("\n", "");
		
		while ( line.length() < limit ) {
			
			for (int i = 0; i < line.length(); i++) {
				if ( line.length() >= limit ) {
					break;
				}
				
				if ( line.charAt(i) == ' ' ) {
					line = line.substring(0, i) + line.substring(i, line.length()).replaceFirst(" ", "  ");
					i++;
				}
			}
					
		}
		
		return line;
	}
	
}
