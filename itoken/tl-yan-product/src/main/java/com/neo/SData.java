package com.neo;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangzhilei on 2017/11/6.
 */
@Slf4j
@Data
public class SData {

    String insureId;
    List<SDataItem> dataItemList = new ArrayList<>();

    public int size() {
        if (dataItemList == null) {
            return 0;
        }
        return dataItemList.size();
    }

    public SDataItem getDataItem(int index) {
        if (index < size()) {
            return dataItemList.get(index);
        }
        return null;
    }

    public void addDataItems(List<SDataItem> dataItems) {
        if (dataItemList == null) {
            dataItemList = new ArrayList<>();
        }
        dataItemList.addAll(dataItems);
    }

    public void addDataItem(SDataItem dataItem) {
        if (dataItemList == null) {
            dataItemList = new ArrayList<>();
        }
        dataItemList.add(dataItem);
    }

    public SDataItem findDataItem(String code) {
        List<SDataItem> dataItems = findDataItems(code);
        if (dataItems.size() > 0) {
            return dataItems.get(0);
        }
        return SDataItem.EMPTY;
    }

    public List<SDataItem> findDataItems(String code) {
        List<SDataItem> dataItems = new ArrayList<>();

        if (dataItemList != null) {
            for (SDataItem dataItem : dataItemList) {
                if (fetch(code, dataItem.code)) {
                    dataItems.add(dataItem);
                }
            }
        }

        return dataItems;
    }

    public List<String> findDataUrls(String code, String customerId) {
        List<String> pathList = new ArrayList<>();

        if (dataItemList != null) {
            for (SDataItem dataItem : dataItemList) {
                if (fetch(code, dataItem.code) && fetch(customerId, dataItem.customerId)) {
                    pathList.add(dataItem.getUrl());
                }
            }
        }

        return pathList;
    }

    static boolean fetch(String source, String str) {
        return source == null || source.equals(str);
    }
}
