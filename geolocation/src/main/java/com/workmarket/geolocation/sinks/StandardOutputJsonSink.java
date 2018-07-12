package com.workmarket.geolocation.sinks;

import com.workmarket.geolocation.domain.GeoLocationResult;
import com.workmarket.geolocation.mappers.GeoLocationResultMapper;

/*
 * This sink writes content to the standard output stream.
 * By default this would go to the console, but the intention
 * of this sink is to be used with the linux redirector (eg. geolocation.jar > result.txt).
 * In this way, a very large file being processed would scale, since it is streamed out to a buffer in the kernel.
 */
public class StandardOutputJsonSink implements Sink<GeoLocationResult> {
    @Override
    public void writeOne(GeoLocationResult item, boolean hasNext) {
        System.out.print(GeoLocationResultMapper.map(item) + (hasNext ? "," : ""));
    }

    @Override
    public void open() {
        System.out.print("[");
    }

    public void close() {
        System.out.print("]");
    }

}
