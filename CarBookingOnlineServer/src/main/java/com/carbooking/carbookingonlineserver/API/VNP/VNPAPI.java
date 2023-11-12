package com.carbooking.carbookingonlineserver.API.VNP;

import com.carbooking.carbookingonlineserver.VNPAY.VNPayService;
import com.carbooking.carbookingonlineserver.entity.Booking;
import com.carbooking.carbookingonlineserver.entity.Payment;
import com.carbooking.carbookingonlineserver.entity.Seat;
import com.carbooking.carbookingonlineserver.service.BookingService;
import com.carbooking.carbookingonlineserver.service.DriverTripService;
import com.carbooking.carbookingonlineserver.service.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.stereotype.Controller
@CrossOrigin(origins = "http://localhost:3000")
public class VNPAPI {
    @Autowired
    private VNPayService vnPayService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private DriverTripService driverTripService;

    @PostMapping("/submitOrder")
    public String submidOrder(@RequestParam("amount") int orderTotal,
                              @RequestParam("orderInfo") String orderInfo,
                              HttpServletRequest request){
        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        String vnpayUrl = vnPayService.createOrder(orderTotal, orderInfo, baseUrl);
        return "redirect:" + vnpayUrl;
    }

    @GetMapping("/vnpay-payment")
    public String GetMapping(HttpServletRequest request){
        int paymentStatus =vnPayService.orderReturn(request);

        String paymentTime = request.getParameter("vnp_PayDate");
        String totalPrice = request.getParameter("vnp_Amount");
        String vnp_TxnRef = request.getParameter("vnp_TxnRef");
        String transactionId = request.getParameter("vnp_TransactionNo");
        String orderInfo = request.getParameter("vnp_OrderInfo");
        String[] parts = orderInfo.split(":");

        if (parts.length > 1) {
            String numberPart = parts[1].trim(); // Lấy phần sau dấu ":" và loại bỏ khoảng trắng
            try {
                Long number = Long.parseLong(numberPart); // Chuyển đổi phần số thành số nguyên
                Booking booking = bookingService.getBookingById(number);
                if(booking != null){
                    if(paymentStatus == 1){
                        Payment payment = new Payment(booking.getId(),"Success",paymentTime,totalPrice,vnp_TxnRef,"03",booking);
                        paymentService.addPayment(payment);
                        return "redirect:http://localhost:3000/payment/success";
                    }else {
                        for (Seat seat: booking.getSeats()){
                            driverTripService.deleteSeat(seat.getId());
                        }

                        bookingService.deleteBookingSeatByBookingId(booking.getId());
                        bookingService.deleteByid(booking.getId());
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Không thể chuyển đổi thành số nguyên.");
            }
        }
        return "redirect:http://localhost:3000/payment/fail";
    }
}
