package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.service;

import java.io.InputStream;

public interface ImageResizeService {

    InputStream resize(InputStream inputStream, int targetHeight, int targetWidth);
}
