package org.tisbus.model;

import org.tisbus.entity.UserMessage;

public class Message {
    private Long id;
    private String name;

    private String text;

    public Message() {
    }

    public static Message toMessage(UserMessage message){
        Message mess = new Message();
        mess.setId(message.getId());
        mess.setName(message.getName());
        mess.setText(message.getText());
        return mess;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
