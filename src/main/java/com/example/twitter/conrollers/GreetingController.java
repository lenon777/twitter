package com.example.twitter.conrollers;

import com.example.twitter.dao.MessageDAO;
import com.example.twitter.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class GreetingController {
    @Autowired
    MessageDAO messageDAO;
    @GetMapping
    public String main(Model model){
        List<Message> messages = messageDAO.findAll();
        model.addAttribute("messages",messages);
        return "main";
    }
    @PostMapping
    public String add(@RequestParam String text,
                      @RequestParam String tag,
                      Model model){
if (text!=null && !text.isEmpty()) {
    Message message = new Message(text, tag);

    messageDAO.save(message);
    List<Message> messages = messageDAO.findAll();
    model.addAttribute("messages", messages);
}else {
    model.addAttribute("enter","enter text please");
    return "main";

}

        return "main";

    }
    @PostMapping("filter")
    public String filter(@RequestParam String filt,
                         Model model){
if (filt!=null && !filt.isEmpty()) {
    List<Message> messages = messageDAO.findByTag(filt);
    model.addAttribute("messages", messages);
}else {
    List<Message> messages = messageDAO.findAll();
    model.addAttribute("messages", messages);
}
        return "main";
    }
    @PostMapping("delete")
    public String delete(@RequestParam int id){
        messageDAO.deleteById(id);

        return "redirect:/";
    }


}
