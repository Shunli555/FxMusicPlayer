package com.shunli.fxmusicplayer;

import javafx.application.Platform;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class FileUtil {

    public interface OnLoadListener {
        void onResult(List<File> results);
    }

    public static void loadFile(String srcPath, Set<String> suffix, OnLoadListener listener) {
        new Thread(() -> {
            File file = new File(srcPath);
            LinkedList<File> queue = new LinkedList<>();
            queue.offer(file);
            File temp;
            ArrayList<File> list = new ArrayList<>();
            while (true) {
                temp = queue.poll();
                if (temp == null) break;
                if (temp.isFile()) {
                    if (suffix.contains(getSuffix(temp.getAbsolutePath()))) list.add(temp);
                    continue;
                }
                File[] child = temp.listFiles();
                if (child == null) {
                    continue;
                }
                for (File ff : child) {
                    queue.offer(ff);
                }
            }
            Platform.runLater(() -> {
                if (listener != null) {
                    listener.onResult(list);
                }
            });
        }).start();
    }

    public static String formatDuration(long duration) {
        if (duration < 1000) {
            return "00:00";
        }
        if (duration < 60 * 1000) {
            return String.format("00:%s", duration);
        }
        if (duration < 60 * 60 * 1000) {
            long min = duration / (60 * 1000);
            long sec = duration % (60 * 1000);
            return String.format("%s:%s", min, sec);
        }
        long hour = duration / (60 * 1000 * 60);
        long min = duration % (60 * 1000 * 10) / (60 * 1000);
        long sec = duration % (60 * 1000 * 60) % (60 * 1000);
        return String.format("%s:%s:%s", hour, min, sec);
    }

    public static List<String> getPaths() {
        File[] files = File.listRoots();
        if (files != null) {
            ArrayList<String> list = new ArrayList<>();
            for (File file : files) {
                list.add(file.getAbsolutePath());
            }
            return list;
        }
        return null;
    }

    public static String getSuffix(String path) {
        int index = path.indexOf(".");
        if (index > 0) {
            return path.substring(index + 1);
        }
        return null;
    }

    public static boolean exists(String path) {
        File file = path == null ? null : new File(path);
        return file != null && file.exists();
    }
}
