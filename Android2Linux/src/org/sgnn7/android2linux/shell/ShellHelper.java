package org.sgnn7.android2linux.shell;

import java.io.File;

import org.sgnn7.android2linux.Linux2Android;
import org.sgnn7.android2linux.util.LogMe;

import android.widget.Toast;

//TODO test me
public class ShellHelper {
	private static final String FILE_SYSTEM_TYPE = "ext2";
	private static final String EXTERNAL_SD_CARD_PATH = "/mnt/sdcard/external_sd/";
	private static final String LOOP_FILE = "/dev/block/loop0";
	private static final String MOUNT_POINT = "/data/local/mnt/Android2Linux";

	private static final String BOOTSTRAP_ARCHIVE_HOST = "www.sgnn7.org";
	private static final String BOOTSTRAP_ARCHIVE = "debian.tar.gz";

	private static final int BUFFER_SIZE = 32768;

	/**
	 * 
	 * Runs command <code>dd of=/mnt/somewhere/blah.test bs=1 count=0 seek=1G</code>
	 * 
	 * @param filename
	 * @param sizeInGb
	 * @return
	 * 
	 */
	public static CommandResult createFile(String filename, int sizeInGb) {
		LogMe.e("Creating file of size " + sizeInGb + "Gb");
		return executeCommand("dd of=" + EXTERNAL_SD_CARD_PATH + "blah.test bs=1 count=0 seek=" + sizeInGb + "M", false);
	}

	/**
	 * Runs command <code>losetup /dev/loop0 /virtualfs /dev/block/loop0 /data/local/mnt/Linux</code> <br />
	 * Result should be <code>/data/local/mnt/Linux ext2 rw,relatime,errors=continue 0 0</code>
	 * 
	 * @param filename
	 * @return
	 */
	public static CommandResult mountLoopFile(String filename) {
		String fullFilename = EXTERNAL_SD_CARD_PATH + filename;
		LogMe.e("Mounting " + fullFilename);
		// CommandResult result = ShellLauncher.invoke("losetup -f");
		// String loopFile = result.getOutput();
		String loopFile = LOOP_FILE;

		LogMe.e("Mounting to " + loopFile);
		return executeCommand("losetup " + loopFile + " " + fullFilename, true);
	}

	/**
	 * Runs command <code>mkfs -t ext3 -m 1 -v /dev/loop0</code>
	 * 
	 * @param filename
	 * @return
	 */
	public static CommandResult formatFile(String filename) {
		String fullFilename = EXTERNAL_SD_CARD_PATH + filename;
		LogMe.e("Formatting file: " + fullFilename);

		return executeCommand("mkfs." + FILE_SYSTEM_TYPE + " -m 1 -v " + LOOP_FILE, true);
	}

	/**
	 * Runs command <code>mount -t ext3 /dev/loop0 /mnt/vfs</code>
	 * 
	 * @return
	 */
	public static CommandResult mountFilesystem() {
		LogMe.e("Mounting loop file: " + LOOP_FILE + " to " + MOUNT_POINT);
		// TODO make sure this is not some crazy directory
		executeCommand("rm -r " + MOUNT_POINT, true);
		executeCommand("mkdir -p " + MOUNT_POINT, true);

		return executeCommand("mount -t " + FILE_SYSTEM_TYPE + " " + LOOP_FILE + " " + MOUNT_POINT, true);
	}

	// TODO be able to change vars
	/**
	 * Get bootstrapper <code>debootstrap --arch armel --foreign squeeze debian --verbose </code>
	 * 
	 * @return
	 */
	public static CommandResult installBootstrapper() {
		String archive_uri = "http://" + BOOTSTRAP_ARCHIVE_HOST + "/debian-armel/" + BOOTSTRAP_ARCHIVE;
		// String ipAddress = null;
		// try {
		// ipAddress = InetAddress.getByName(BOOTSTRAP_ARCHIVE_HOST).getHostAddress();
		// } catch (UnknownHostException e) {
		// LogMe.e(e);
		// }
		//
		//
		// LogMe.e("Installing bootstrapper to " + MOUNT_POINT + " from " + archive_uri);
		// CommandResult result = executeCommand("wget --header \"Host: " + BOOTSTRAP_ARCHIVE_HOST + "\" -p "
		// + MOUNT_POINT + " " + archive_uri, true);
		// if (result.hadErrors()) {
		// Toast.makeText(Linux2Android.getInstance(), "Could not download bootstrap file. Exiting.",
		// Toast.LENGTH_LONG).show();
		// }

		Toast.makeText(Linux2Android.getInstance(), "Started downloading: " + archive_uri, Toast.LENGTH_LONG).show();
		File outputFile = new File(EXTERNAL_SD_CARD_PATH, BOOTSTRAP_ARCHIVE);
		// try {
		// outputFile.delete();
		// FileOutputStream fos = new FileOutputStream(outputFile);
		//
		// URLConnection urlConnection = new URL(archive_uri).openConnection();
		// InputStream is = urlConnection.getInputStream();
		// BufferedInputStream bis = new BufferedInputStream(is);
		//
		// byte[] buffer = new byte[BUFFER_SIZE];
		// int length = 0;
		// while ((length = bis.read(buffer)) != -1) {
		// fos.write(buffer, 0, length);
		// }
		// fos.close();
		// bis.close();
		// is.close();
		//
		// Toast.makeText(Linux2Android.getInstance(), "Downloaded: " + outputFile.getName(), Toast.LENGTH_LONG)
		// .show();
		// } catch (Exception e) {
		// // Diaper
		// LogMe.e(e);
		// return null;
		// }

		executeCommand("tar -xf " + outputFile.getAbsolutePath() + " -C " + MOUNT_POINT, true);

		return null;
	}

	// umount /mnt/vfs
	// losetup -d /dev/loop0

	private static CommandResult executeCommand(String command, boolean asSuperuser) {
		CommandResult result = ShellLauncher.invoke(command, asSuperuser);
		LogMe.e(result);
		LogMe.e("Done");

		if (result.hadErrors()) {
			Toast.makeText(Linux2Android.getInstance(), "Command Failed: " + command, Toast.LENGTH_LONG);
			Toast.makeText(Linux2Android.getInstance(), result.getOutput(), Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(Linux2Android.getInstance(), "Command Success: " + command, Toast.LENGTH_SHORT).show();
		}

		return result;
	}
}
