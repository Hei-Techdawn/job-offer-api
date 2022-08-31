package com.example.initialapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataFormat<T> {
    private int lastPage;
    private int currentPage;
    private List<T> data;

    public void format(int page, int size,int countAll) {
        this.setCurrentPage(page);
        int count = countAll;
        int lastPage = 0;
        while (count > 0) {
            lastPage += 1;
            count -= size;
        }
        this.setLastPage(lastPage);
    }
}
