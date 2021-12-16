package com.dissi.adventofcode.version2021.day16;

import java.util.ArrayList;
import java.util.List;
import lombok.ToString;
import lombok.extern.java.Log;

@ToString
@Log
public class Packet {

    private final List<Packet> iHazPacket = new ArrayList<>();
    private final int version;
    private final int id;

    private String myPacket;
    private int length = 0;
    private long value = -1;

    public Packet(String all, int currentIndex) {
        this.myPacket = all.substring(currentIndex);
        version = readInteger(3);
        id = readInteger(3);

        if (id == 4) {
            readValue();
        } else {
            readOperator();
        }
        this.myPacket = myPacket.substring(0, length);
    }

    private void readValue() {
        boolean shouldContinue = true;
        StringBuilder total = new StringBuilder();
        while (shouldContinue) {
            shouldContinue = readBoolean();
            total.append(readString(4));
        }
        value = Long.parseLong(total.toString(), 2);
    }

    private void readOperator() {
        boolean iEnabled = readBoolean();
        int readLength;
        if (iEnabled) {
            readLength = readInteger(11);
            for (int i = 0; i < readLength; i++) {
                Packet packet = new Packet(this.myPacket, length);
                iHazPacket.add(packet);
                length += packet.length;
            }
        } else {
            readLength = readInteger(15);
            String extraPacket = readString(readLength);
            while (readLength > 0) {
                Packet packet = new Packet(extraPacket, extraPacket.length() - readLength);
                iHazPacket.add(packet);
                readLength -= packet.length;
            }
        }
    }

    public long getVersionSum() {
        long sum = this.version;
        for (Packet p : iHazPacket) {
            sum += p.getVersionSum();
        }
        return sum;
    }

    public long getValue() {
        if (this.value != -1) {
            return value;
        }

        return switch (id) {
            case 0 -> iHazPacket.stream().mapToLong(Packet::getValue).sum();
            case 1 -> iHazPacket.stream().mapToLong(Packet::getValue).reduce((a, b) -> a * b).getAsLong();
            case 2 -> iHazPacket.stream().mapToLong(Packet::getValue).min().getAsLong();
            case 3 -> iHazPacket.stream().mapToLong(Packet::getValue).max().getAsLong();
            case 5 -> iHazPacket.get(0).getValue() > iHazPacket.get(1).getValue() ? 1L : 0L;
            case 6 -> iHazPacket.get(0).getValue() < iHazPacket.get(1).getValue() ? 1L : 0L;
            case 7 -> iHazPacket.get(0).getValue() == iHazPacket.get(1).getValue() ? 1L : 0L;
            default -> throw new IllegalStateException("Not known id: " + id);
        };
    }

    private int readInteger(int size) {
        int toReturn = PacketUtils.binToInt(myPacket, length, size);
        length += size;
        return toReturn;
    }

    private String readString(int size) {
        String toReturn = myPacket.substring(length, length + size);
        length += size;
        return toReturn;
    }

    private boolean readBoolean() {
        boolean toReturn = PacketUtils.binToBool(myPacket, length);
        length += 1;
        return toReturn;
    }
}
