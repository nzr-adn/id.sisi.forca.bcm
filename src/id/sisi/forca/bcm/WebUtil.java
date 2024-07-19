package id.sisi.forca.bcm;

import java.util.logging.Level;

import javax.servlet.http.HttpServletRequest;

import org.compiere.util.CLogger;
import org.compiere.util.WebEnv;

public class WebUtil {

	private static CLogger log = CLogger.getCLogger(WebUtil.class);

	public static String getParameter(HttpServletRequest request, String parameter) {
		if (request == null || parameter == null)
			return null;
		String enc = request.getCharacterEncoding();
		try {
			if (enc == null) {
				request.setCharacterEncoding(WebEnv.ENCODING);
				enc = request.getCharacterEncoding();
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "Set CharacterEncoding=" + WebEnv.ENCODING, e);
			enc = request.getCharacterEncoding();
		}
		String data = request.getParameter(parameter);
		if (data == null || data.length() == 0)
			return data;

		// Convert
		if (enc != null && !WebEnv.ENCODING.equals(enc)) {
			try {
				String dataEnc = new String(data.getBytes(enc), WebEnv.ENCODING);
				if (log.isLoggable(Level.FINER))
					log.log(Level.FINER,
							"Convert " + data + " (" + enc + ")-> " + dataEnc + " (" + WebEnv.ENCODING + ")");
				data = dataEnc;
			} catch (Exception e) {
				log.log(Level.SEVERE, "Convert " + data + " (" + enc + ")->" + WebEnv.ENCODING);
			}
		}

		// Convert &#000; to character (JSTL input)
		String inStr = data;
		StringBuilder outStr = new StringBuilder();
		int i = inStr.indexOf("&#");
		while (i != -1) {
			outStr.append(inStr.substring(0, i)); // up to &#
			inStr = inStr.substring(i + 2, inStr.length()); // from &#

			int j = inStr.indexOf(';'); // next ;
			if (j < 0) // no second tag
			{
				inStr = "&#" + inStr;
				break;
			}

			String token = inStr.substring(0, j);
			try {
				int intToken = Integer.parseInt(token);
				outStr.append((char) intToken); // replace context
			} catch (Exception e) {
				log.log(Level.SEVERE, "Token=" + token, e);
				outStr.append("&#").append(token).append(";");
			}
			inStr = inStr.substring(j + 1, inStr.length()); // from ;
			i = inStr.indexOf("&#");
		}

		outStr.append(inStr); // add remainder
		String retValue = outStr.toString();

		if (log.isLoggable(Level.FINEST))
			log.finest(parameter + "=" + data + " -> " + retValue);
		return retValue;
	}
}
