import axiosClient from "~/api/axiosClient";

const EmployeeAPI = {
    add: (body) => {
        const url = '/api/v1/owner/employees';
        return axiosClient.post(url,body);
    },

    getone: (phone) => {
        const url = `/api/v1/owner/employees/${phone}`;
        return axiosClient.get(url);
    },

    getlistbycompany: (phoneCompany) => {
        const url = `/api/v1/owner/employees/company/${phoneCompany}`;
        return axiosClient.get(url);
    },

    deleteEmployee: (phone) => {
        const url = `/api/v1/owner/employees/${phone}`;
        return axiosClient.delete(url);
    },
    update: (phone,body) => {
        const url = `/api/v1/owner/employees/${phone}`;
        return axiosClient.put(url,body);
    }

}

export default EmployeeAPI;