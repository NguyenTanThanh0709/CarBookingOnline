import axiosClient from "~/api/axiosClient";

const TripAPI = {
    getlist: (idcompany) => {
        const url = `/api/v1/staff/trip/company/${idcompany}`;
        return axiosClient.get(url);
    },
    addone: (body) =>{
        const url = `/api/v1/staff/trip`;
        return axiosClient.post(url,body);
    },
    editone :(id,body) =>{
        const url = `/api/v1/staff/trip/${id}`;
        return axiosClient.put(url,body);
    }
}

export default TripAPI;