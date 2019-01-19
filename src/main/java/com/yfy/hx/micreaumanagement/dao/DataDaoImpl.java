package com.yfy.hx.micreaumanagement.dao;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class DataDaoImpl implements DataDao{
    private List<String> phHistory = new ArrayList<>();
    private List<String> tempHistory = new ArrayList<>();

    @Override
    public void savePhData(String ph) {
        phHistory.add(ph);
    }

    @Override
    public List<String> getPastPhData() {
        return Collections.unmodifiableList(phHistory);
    }

    @Override
    public void saveTempData(String temp) {
        tempHistory.add(temp);
    }

    @Override
    public List<String> getPastTempData() {
        return Collections.unmodifiableList(tempHistory);
    }
}
