package net.m3u8.listener;

public interface DownloadListener {

    void start();

    void process(String downloadUrl, int finished, int sum, float percent) throws InterruptedException;

    void speed(String speedPerSecond);

    void end();

}
