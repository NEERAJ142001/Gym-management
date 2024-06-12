package com.example.gym.service;

import com.example.gym.entity.Slot;

import java.util.Date;
import java.util.List;

public interface SlotService {
    List<Slot> getAllSlots();

    Slot saveSlot(Slot slot);

    boolean isSlotAvailable(Date date, String timing);

    // Add other methods as needed
}
