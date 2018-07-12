package com.workmarket.geolocation.sources;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.Stream;

public class FileSource implements Source<String> {

    private final Iterator<String> iterator;

    public FileSource(String path) throws IOException {
        //Using streaming here so that a very large file will scale.
        Stream<String> stream = Files.lines(Paths.get(path));
        iterator = stream.iterator();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public String getNext() {
        return iterator.next();
    }
}
