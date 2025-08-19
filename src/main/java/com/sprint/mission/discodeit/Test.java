package com.sprint.mission.discodeit;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<File> files = Arrays.stream(Path.of("Abs").toFile().listFiles()).toList();
        System.out.println(files);
        System.out.println(Path.of("Channel").toFile());
    }
}
