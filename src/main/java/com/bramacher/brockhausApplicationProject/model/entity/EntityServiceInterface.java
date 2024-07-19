package com.bramacher.brockhausApplicationProject.model.entity;

import java.io.BufferedReader;

public interface EntityServiceInterface<P> {

    void readAndSaveCsvData(BufferedReader csv);
}
