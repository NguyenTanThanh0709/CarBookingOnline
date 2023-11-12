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
}

export default BookingAPI;