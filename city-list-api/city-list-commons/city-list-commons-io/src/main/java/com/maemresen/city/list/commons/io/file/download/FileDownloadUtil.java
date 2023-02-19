package com.maemresen.city.list.commons.io.file.download;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

/**
 * Helper methods to download files
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FileDownloadUtil {

	/**
	 * Download and save file to the filesystem
	 *
	 * @param urlString        location of the file
	 * @param fileOutputStream stream that file will be written
	 * @throws FileDownloadException any kind of error while saving file
	 */
	public static void downloadAndSave(String urlString, FileOutputStream fileOutputStream) throws FileDownloadException {
		try {
			fileOutputStream.write(downloadAndGetBytes(urlString));
		} catch (Exception exception) {
			throw new FileDownloadException(String.format("%s download error", urlString), exception);
		}
	}

	/**
	 * Download and get bytes of the file at given url
	 *
	 * @param urlString location of the file
	 * @return file bytes as an array
	 * @throws FileDownloadException any kind of error while getting bytes
	 */
	public static byte[] downloadAndGetBytes(String urlString) throws FileDownloadException {
		try {
			URL url = new URL(urlString);
			try (InputStream in = new BufferedInputStream(url.openStream());
				 ByteArrayOutputStream out = new ByteArrayOutputStream()) {
				byte[] buf = new byte[1024];
				int n = 0;
				while (-1 != (n = in.read(buf))) {
					out.write(buf, 0, n);
				}
				return out.toByteArray();
			}
		} catch (Exception exception) {
			throw new FileDownloadException(String.format("%s download error", urlString), exception);
		}
	}
}
