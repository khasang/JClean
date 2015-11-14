package ru.khasang.jclean.module;

/**
 * Created by demon on 14.11.2015.
 */
public class StatsReport {
    private String typeName;
    private long amount;
    private long size;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
