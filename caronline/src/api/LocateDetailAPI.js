
import axiosClient from "~/api/axiosClient";

const LocateDetailAPI = {

    getlistPICKUP: (id) => {
        const url = `/api/v1/staff/pickup/trip/${id}`;
        return axiosClient.get(url);
    },
    getlistDROPOFF: (id) => {
        const url = `/api/v1/staff/dropoff/trip/${id}`;
        return axiosClient.get(url);
    },


    updatePICKUP: (id,body) => {
        const url = `/api/v1/staff/pickup/${id}`;
        return axiosClient.put(url,body);
    },
    updateDROPOFF: (id,body) => {
        const url = `/api/v1/staff/dropoff/${id}`;
        return axiosClient.put(url,body);
    },


    addonePICKUP: (body) => {
        const url = `/api/v1/staff/pickup`;
        return axiosClient.post(url,body);
    },
    addoneDROPOFF: (body) => {
        const url = `/api/v1/staff/dropoff`;
        return axiosClient.post(url,body);
    },


    deletePICKUP: (id) => {
        const url = `/api/v1/staff/pickup/${id}`;
        return axiosClient.delete(url);
    },
    deleteDROPOFF: (id) => {
        const url = `/api/v1/staff/dropoff/${id}`;
        return axiosClient.delete(url);
    },














    getone: (id) => {
        const url = `/api/v2/admin/typecar/${id}`;
        return axiosClient.get(url);
    },

    
    

}

export default LocateDetailAPI;