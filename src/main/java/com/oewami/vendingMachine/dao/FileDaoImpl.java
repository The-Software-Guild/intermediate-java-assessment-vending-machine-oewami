package com.oewami.vendingMachine.dao;

import com.oewami.vendingMachine.dto.Item;

import java.io.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileDaoImpl implements FileDao {

    private final String DELIMITER = ",";
    private final String ITEM_FILE = "items.txt";

    @Override
    public Item unmarshallItem(String line) {
        String[] details = line.split(DELIMITER);
        Item item = new Item();
        item.setName(details[0]);
        item.setCost(new BigDecimal(details[1]));
        item.setInventory(Integer.parseInt(details[2]));
        return item;
    }

    @Override
    public String marshallItem(Item item) {
        StringBuilder builder = new StringBuilder();
        builder.append(item.getName() + DELIMITER);
        builder.append(item.getCost() + DELIMITER);
        builder.append(item.getInventory() + System.lineSeparator());
        return builder.toString();
    }

    @Override
    public void writeFile(List<Item> list) {
        StringBuilder builder = new StringBuilder();
        for(Item item : list) {
            builder.append(marshallItem(item));
        }
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(ITEM_FILE));
            writer.write(builder.toString());
            writer.close();
        } catch(IOException e) {
            System.out.println("COULD NOT WRITE TO FILE");
        }
    }

    @Override
    public Map<String, Item> readFile(String file) {
        String line;
        Map<String, Item> inventory = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while((line = reader.readLine()) != null) {
                Item item = unmarshallItem(line);
                inventory.put(item.getName(), item);
            }
        } catch(IOException e) {
            System.out.println("COULD NOT READ FILE");
        }
        return inventory;
    }
}
