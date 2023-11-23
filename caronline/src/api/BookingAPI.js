import axiosClient from "~/api/axiosClient";

const BookingAPI = {
    getlist: (iddriverttrip) => {
        const url = `/api/v1/user/booking/trip?iddrivertrip=${iddriverttrip}`;
        return axiosClient.get(url);
    },
    addOneBooking :(body) =>{
        const url = `/api/v1/user/booking`;
        return axiosClient.post(url,body);
    }
    ,
    getlistBookingByUser :(phone) =>{
        const url = `/api/v1/user/booking/user?phoneuser=${phone}`;
        return axiosClient.get(url);
    },
    getlistPaymentbyIdBooking :(id) =>{
        const url = `/api/v1/user/payment/${id}`;
        return axiosClient.get(url);
    },
    getone : (id) =>{
        const url = `/api/v1/user/booking/${id}`;
        return axiosClient.get(url);
    },
    refund : (bookingid,orderId,amountt,transDate,user) =>{
        const url = `/api/v1/user/refund?bookingid=${bookingid}&orderId=${orderId}&amountt=${amountt}&transDate=${transDate}&user=${user}`;
        return axiosClient.post(url);
    },
    promotion :(idtrip, code) =>{
        const url = `/api/v1/user/promotion/byCodeAndTripId/${code}/${idtrip}`;
        return axiosClient.get(url);
    }
}

export default BookingAPI;