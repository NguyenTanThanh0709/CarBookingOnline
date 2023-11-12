package com.carbooking.carbookingonlineserver.service;

public interface IMailService {
    void sendHtmlEmail(String picup, String dropoff, String date,String price, String to);
}
