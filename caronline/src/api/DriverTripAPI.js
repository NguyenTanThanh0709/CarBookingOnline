
import axiosClient from "~/api/axiosClient";

const DriverTripAPI = {
   
    getone: (id) => {
        const url = `/api/v1/staff/drivertrip/${id}`;
        return axiosClient.get(url);
    },
    addone: (body) =>{
        const url = `/api/v1/staff/drivertrip`;
        return axiosClient.post(url,body);
    },
    editone :(id,body) =>{
        const url = `/api/v1/staff/drivertrip/${id}`;
        return axiosClient.put(url,body);
    },
    getlistDriverForCompany: (role,phonecompany) => {
        const url = `/api/v1/staff/drivertrip/employees?phoneCompany=${phonecompany}&role=${role}`;
        return axiosClient.get(url);
    },

    getlistByIDTRIP: (idtrip) => {
        const url = `/api/v1/staff/drivertrip/listbytrip?idtrip=${idtrip}`;
        return axiosClient.get(url);
    },
    getlistBYDATEANDTRIP: (date,idtrip) => {
        const url = `/api/v1/staff/drivertrip/listbygraterdatetrip?idtrip=${idtrip}&date=${date}`;
        return axiosClient.get(url);
    },
    getlistByDATE :(phonecompany,date) =>{
        const url = `/api/v1/staff/drivertrip/listbydate?phonecompany=${phonecompany}&date=${date}`;
        return axiosClient.get(url);
    },

    getlistDriverTripAfterDate : (date,phonecompany) =>{
        const url = `/api/v1/staff/drivertrip/listbydategrate?phonecompany=${phonecompany}&date=${date}`;
        return axiosClient.get(url);
    },
    deleteById :(id) =>{
        const url = `/api/v1/staff/drivertrip?id=${id}`;
        return axiosClient.delete(url);
    },
    getlistDriverTripAfterDateANDTRIP : (date,idtrip) =>{
        const url = `/api/v1/staff/drivertrip/listtripdategreate?idtrip=${idtrip}&date=${date}`;
        return axiosClient.get(url);
    },

    getByDateLocateAll : (locatestart,locateend,date) =>{
        const url = `/api/v1/user/drivertrip/listbydate?locatestart=${locatestart}&locateend=${locateend}&date=${date}`;
        return axiosClient.get(url);
    }
}

export default DriverTripAPI;