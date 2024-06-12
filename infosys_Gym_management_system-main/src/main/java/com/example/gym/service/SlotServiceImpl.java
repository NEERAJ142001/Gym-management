package com.example.gym.service;

import com.example.gym.entity.Slot;
import com.example.gym.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SlotServiceImpl implements SlotService {

    @Autowired
    private SlotRepository slotRepository;

    @Override
    public List<Slot> getAllSlots() {
        return slotRepository.findAll();
    }

    @Override
    public Slot saveSlot(Slot slot) {
        return slotRepository.save(slot);
    }

    @Override
    public boolean isSlotAvailable(Date date, String timing) {
        return !slotRepository.findByDateAndTiming(date, timing).isPresent();
    }
    
    // Implement other methods as needed
}
