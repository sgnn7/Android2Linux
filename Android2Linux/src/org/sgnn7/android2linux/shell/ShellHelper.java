package org.sgnn7.android2linux.shell;

import org.sgnn7.android2linux.util.LogMe;

//TODO test me
public class ShellHelper {
	private static final String FILE_SYSTEM_TYPE = "ext2";
	private static final String EXTERNAL_SD_CARD_PATH = "/mnt/sdcard/external_sd/";
	private static final String LOOP_FILE = "/dev/block/loop0";
	private static final String MOUNT_POINT = "/data/local/mnt/Android2Linux";

	// static String LOOP_FILE = "/dev/block/loop0";

	public static CommandResult createFile(String filename, int sizeInGb) {
		LogMe.e("Creating file of size " + sizeInGb + "Gb");

		// dd of=/mnt/somewhere/blah.test bs=1 count=0 seek=1G
		CommandResult result = ShellLauncher.invoke("dd of=" + EXTERNAL_SD_CARD_PATH + "blah.test bs=1 count=0 seek="
				+ sizeInGb + "G");

		LogMe.e(result);
		LogMe.e("Done");

		return result;
	}

	// losetup -f
	// losetup /dev/loop0 /virtualfs
	// /dev/block/loop0 /data/local/mnt/Linux/data/local/mnt/Linux ext2 rw,relatime,errors=continue 0 0
	public static CommandResult mountLoopFile(String filename) {
		String fullFilename = EXTERNAL_SD_CARD_PATH + filename;
		LogMe.e("Mounting " + fullFilename);
		// CommandResult result = ShellLauncher.invoke("losetup -f");
		// String loopFile = result.getOutput();
		String loopFile = LOOP_FILE;

		LogMe.e("Mounting to " + loopFile);
		CommandResult result = ShellLauncher.invoke("losetup " + loopFile + " " + fullFilename, true);
		LogMe.e(result);
		LogMe.e("Done");

		return result;
	}

	// mkfs -t ext3 -m 1 -v /dev/loop0
	public static CommandResult formatFile(String filename) {
		String fullFilename = EXTERNAL_SD_CARD_PATH + filename;
		LogMe.e("Formatting file: " + fullFilename);
		CommandResult result = ShellLauncher.invoke("mkfs." + FILE_SYSTEM_TYPE + " -m 1 -v " + LOOP_FILE, true);
		LogMe.e(result);
		LogMe.e("Done");

		return result;
	}

	// mount -t ext3 /dev/loop0 /mnt/vfs
	public static CommandResult mountFilesystem() {
		LogMe.e("Mounting loop file: " + LOOP_FILE + " to " + MOUNT_POINT);
		// TODO make sure this is not some crazy directory
		ShellLauncher.invoke("rm -r " + MOUNT_POINT, true);
		CommandResult result = ShellLauncher.invoke("mkdir -p " + MOUNT_POINT, true);

		result = ShellLauncher.invoke("mount -t " + FILE_SYSTEM_TYPE + " " + LOOP_FILE + " " + MOUNT_POINT, true);
		LogMe.e(result);
		LogMe.e("Done");

		return result;
	}

	// umount /mnt/vfs
	// losetup -d /dev/loop0
}
