package com.example.gym.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.gym.entity.Slot;
import com.example.gym.service.SlotService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class SlotController {

    @Autowired
    private SlotService slotService;

    @GetMapping("/slot-booking")
    public String showSlotForm() {
        return "slot"; // Assuming you have a "slot.html" template
    }

    @PostMapping("/saveSlot")
    public String saveSlot(@RequestParam("date") String dateString,
                           @RequestParam("timing") String timing,
                           @RequestParam("dietPlan") String dietPlan,
                           @RequestParam("specialRequirements") String specialRequirements,
                           RedirectAttributes redirectAttributes) {

        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);

            // Check if the slot is already booked
            if (!slotService.isSlotAvailable(date, timing)) {
                redirectAttributes.addFlashAttribute("message", "Slot is already booked!");
                return "redirect:/slot-booking";
            }

            // Slot is available, save it
            Slot slot = new Slot();
            slot.setDate(date);
            slot.setTiming(timing);
            slot.setDietPlan(dietPlan);
            slot.setSpecialRequirements(specialRequirements);

            slotService.saveSlot(slot);

            redirectAttributes.addFlashAttribute("message", "Slot booked successfully!");
        } catch (ParseException e) {
            redirectAttributes.addFlashAttribute("message", "Invalid date format!");
        }

        return "redirect:/slot-booking";
    }
}
