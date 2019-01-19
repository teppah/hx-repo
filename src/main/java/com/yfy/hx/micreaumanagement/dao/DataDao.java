package com.yfy.hx.micreaumanagement.dao;

import java.util.List;

public interface DataDao {
    void savePhData(String ph);
    List<String> getPastPhData();

    void saveTempData(String temp);
    List<String> getPastTempData();
}
