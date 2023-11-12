import axiosClient from "~/api/axiosClient";

const CarAPI = {
    getlist: (phonecompany) => {
        const url = `/api/v1/owner/cars/company/${phonecompany}`
        return axiosClient.get(url);
    },
    getone: (id) => {
        const url = `/api/v1/owner/cars/${id}`;
        return axiosClient.get(url);
    },

    addone: (body) => {
        const url = `/api/v1/owner/cars`;
        return axiosClient.post(url,body);
    },
    update: (id,status) =>{
        const url = `/api/v1/owner/cars/${id}/availability?status=${status}`;
        return axiosClient.put(url);
    }

}

export default CarAPI;