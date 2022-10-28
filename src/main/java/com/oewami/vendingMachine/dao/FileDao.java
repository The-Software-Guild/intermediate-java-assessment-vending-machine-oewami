package com.oewami.vendingMachine.dao;

import com.oewami.vendingMachine.dto.Item;
import java.util.List;
import java.util.Map;

public interface FileDao {
    public Item unmarshallItem(String line);
    public String marshallItem(Item item);
    public void writeFile(List<Item> list);
    public Map<String,Item> readFile(String file);
}
