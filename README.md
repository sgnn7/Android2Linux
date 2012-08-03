Android2Linux
=============



Android app that can be used to install Linux and run it in parallel with Android
Currently, it is in pre-Alpha state, works only on rooted Android devices with su+busybox, and does not install anything yet.


List of currently (albeit badly) implemented features:

- Creation of loop files (currently hardcoded to /mnt/sdcard/external_sd)
- Association of loop files
- Mounting of loop files
- Formatting of loop files (ext only for now)
- Mounting the loop file to filesystem