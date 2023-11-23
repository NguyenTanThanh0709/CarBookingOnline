package com.carbooking.carbookingonlineserver.service;

public interface IMailService {
    void senMailRenfund(String to);
    void sendHtmlEmail(String picup, String dropoff, String date,String price, String to);
    void sendMailPassword(String Pass, String to);
}
