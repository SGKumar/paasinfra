package com.atlassianold.ds;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;

public class DirSize {
  public static List<String> getTopDirsFlat(List<FileInfo> filesnDirs, int n) {

    // What if all are files w no directories ?
    // what if all are directories ?
    // what if top k is really (k+n) ?
    Map<String, Integer> dirFiles = new HashMap<String, Integer>();
    for(FileInfo file : filesnDirs) {
      dirFiles.putIfAbsent(file.getDirName(), 0);
      Integer size = dirFiles.get(file.getDirName());
      size += file.getFileSize();
      dirFiles.put(file.getDirName(), size);
    }

    ArrayList<Pair<String, Integer>> dirSizes = new ArrayList<>();
    for(String dir : dirFiles.keySet()) {
      dirSizes.add(Pair.of(dir, dirFiles.get(dir)));
    }

    System.out.println(dirSizes.toString());

    Comparator<Pair<String, Integer>> dirSizeComparator = Comparator.comparing(Pair::getRight);
    Collections.sort(dirSizes, dirSizeComparator.reversed());

    return dirSizes.stream().map(dirSize -> dirSize.getKey()).limit(n).collect(Collectors.toList());
  }
  
  public static List<String> getTopDirsNested(List<FileInfo> filesnDirs, int n) {

    // What if all are files w no directories ?
    // what if all are directories ?
    // what if top k is really (k+n) ?
    /*
    Map<FileInfo, List<FileInfo>> dirFiles = new HashMap<>();
    for(FileInfo file : filesnDirs) {
      dirFiles.putIfAbsent(new FileInfo(file.getDirName(), null, 0), null);
      Integer size = dirFiles.get(file.getDirName());
      size += file.getFileSize();
      dirFiles.put(file.getDirName(), size);
    }

    for(FileInfo file : filesnDirs) {
      String dir = currFile.getDirName();
      FileInfo currDir = dirFiles.get(file.getDirName());
      while(currFile != null) {
        dirFiles.putIfAbsent(dir, 0);
        Integer size = dirFiles.get(file.getDirName());
        size += file.getFileSize();
      }
    }

    ArrayList<Pair<String, Integer>> dirSizes = new ArrayList<>();
    for(String dir : dirFiles.keySet()) {
      dirSizes.add(Pair.of(dir, dirFiles.get(dir)));
    }

    Comparator<Pair<String, Integer>> dirSizeComparator = Comparator.comparing(Pair::getRight);
    Collections.sort(dirSizes, dirSizeComparator.reversed());

    return dirSizes.stream().map(dirSize -> dirSize.getKey()).limit(n).collect(Collectors.toList());
    */
    return null;
  }

  public static void main(String[] args) {
    System.out.println(FileSystems.getDefault().getPathMatcher("glob:" + "/account/**").matches(Paths.get("/account/key-id")));
    System.out.println(FileSystems.getDefault().getPathMatcher("glob:" + "/account/*").matches(Paths.get("/account/key-id")));
    System.out.println(FileSystems.getDefault().getPathMatcher("glob:" + "/account").matches(Paths.get("/account")));
    flatDirs();
  }

  private static void flatDirs() {
    List<FileInfo> files = new ArrayList<>();
    files.add(new FileInfo("aaa", "dir1", 20));
    files.add(new FileInfo("aab", "dir1", 10));
    files.add(new FileInfo("aac", "dir1", 15));
    files.add(new FileInfo("daa", "dir2", 20));
    files.add(new FileInfo("dab", "dir2", 20));
    files.add(new FileInfo("dac", "dir2", 20));
    files.add(new FileInfo("baa", "dir3", 50));
    files.add(new FileInfo("eaa", "dir4", 40));

    System.out.println(getTopDirsFlat(files, 5));
  }

  private static void nestedDirs() {
    List<FileInfo> files = new ArrayList<>();
    files.add(new FileInfo("aaa", "dir1", 20));
    files.add(new FileInfo("aab", "dir1", 10));
    files.add(new FileInfo("aac", "dir1", 15));
    files.add(new FileInfo("daa", "dir2", 20));
    files.add(new FileInfo("dab", "dir2", 20));
    files.add(new FileInfo("dac", "dir2", 20));
    files.add(new FileInfo("baa", "dir3", 50));
    files.add(new FileInfo("eaa", "dir4", 40));
    files.add(new FileInfo("dir2", "d2", 0));
    files.add(new FileInfo("dir4", "d2", 0));
    files.add(new FileInfo("dir1", "d1", 0));
    files.add(new FileInfo("dir3", "d1", 0));
    files.add(new FileInfo("d2", "dir", 0));
    files.add(new FileInfo("d1", "dir", 0));

    //System.out.println(getTopDirsNested(files, 5));

    List<Integer> list = new ArrayList<>();
    //list.remove(index);

  }
}
