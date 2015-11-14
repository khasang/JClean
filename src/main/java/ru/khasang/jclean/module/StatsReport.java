package ru.khasang.jclean.module;

public class StatsReport {
    private String typeName;
    private long amount;
    private long size;

    public StatsReport() {
    }

    public StatsReport(String typeName, long amount, long size) {
        this.typeName = typeName;
        this.amount = amount;
        this.size = size;
    }

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
