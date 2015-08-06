package vn.toancauxanh.mis.nddk.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PhotoHandler extends HttpServlet {

	private static final long serialVersionUID = -3353210616900132576L;
	private static final Logger logger = LoggerFactory.getLogger(PhotoHandler.class);
	// Constants
	// ----------------------------------------------------------------------------------

	private static final int DEFAULT_BUFFER_SIZE = 10240; // 10KB.

	// Properties
	// ---------------------------------------------------------------------------------

	protected String imagePath;

	// Actions
	// ------------------------------------------------------------------------------------

	@Override
	public void init() {

		// Define base path somehow. You can define it as init-param of the
		// servlet.
		this.imagePath = "/photo";

		// In a Windows environment with the Applicationserver running on the
		// c: volume, the above path is exactly the same as "c:\images".
		// In UNIX, it is just straightforward "/images".
		// If you have stored files in the WebContent of a WAR, for example in
		// the
		// "/WEB-INF/images" folder, then you can retrieve the absolute path by:
		// this.imagePath = getServletContext().getRealPath("/WEB-INF/images");
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// Get requested image by path info.
		String requestedImage = request.getParameter("uri");
		logger.info("image uri:"+request.getParameter("uri"));
		// Check if file name is actually supplied to the request URI.
		if (requestedImage == null) {
			// Do your thing if the image is not supplied to the request URI.
			// Throw an exception, or send 404, or show default/warning image,
			// or just ignore it.
			response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
			return;
		}

		// Decode the file name (might contain spaces and on) and prepare file
		// object.
		File image = new File(URLDecoder.decode(requestedImage, "UTF-8"));
		// Check if file actually exists in filesystem.
		if (!image.exists()) {
			// Do your thing if the file appears to be non-existing.
			// Throw an exception, or send 404, or show default/warning image,
			// or just ignore it.
			response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
			return;
		}

		// Get content type by filename.
		String contentType = getServletContext().getMimeType(image.getName());

		// Check if file is actually an image (avoid download of other files by
		// hackers!).
		// For all content types, see:
		// http://www.w3schools.com/media/media_mimeref.asp
		if (contentType == null || !contentType.startsWith("image")) {
			// Do your thing if the file appears not being a real image.
			// Throw an exception, or send 404, or show default/warning image,
			// or just ignore it.
			response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
			return;
		}
		logger.info("image"+image.getName());
		// Init servlet response.
		response.reset();
		response.setBufferSize(DEFAULT_BUFFER_SIZE);
		response.setContentType(contentType);
		response.setHeader("Content-Length", String.valueOf(image.length()));
		response.setHeader("Content-Disposition",
				"inline; filename=\"" + image.getName() + "\"");
		
		// Prepare streams.
		try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(image),
				DEFAULT_BUFFER_SIZE)) {
		BufferedOutputStream output = null;

		try {
			// Open streams.
			output = new BufferedOutputStream(response.getOutputStream(),
					DEFAULT_BUFFER_SIZE);

			// Write file contents to response.
			byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
			int length;
			while ((length = input.read(buffer)) > 0) {
				output.write(buffer, 0, length);
			}
		} finally {
			// Gently close streams.
			close(output);
			close(input);
		}
		}
	}

	// Helpers (can be refactored to public utility class)
	// ----------------------------------------

	private static void close(Closeable resource) {
		if (resource != null) {
			try {
				resource.close();
			} catch (IOException e) {
				// Do your thing with the exception. Print it, log it or mail
				// it.
				e.printStackTrace();
			}
		}
	}
}