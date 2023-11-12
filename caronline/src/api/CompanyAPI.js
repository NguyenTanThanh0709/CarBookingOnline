import axiosClient from "~/api/axiosClient";

const CompanyAPI = {
    addCompany: (body) => {
        const url = '/api/v2/admin/companies';
        return axiosClient.post(url, body);
    },

    getCompanyByPhone: (phone) => {
        const url = `/api/v2/admin/companies/${phone}`;
        return axiosClient.get(url);
    },

    getListCompanyActivy: (status) => {
        const url = `/api/v2/admin/companies?status=${status}`;
        return axiosClient.get(url);
    },
    updateCompany: (phone,body) => {
        const url = `/api/v2/admin/companies/${phone}`;
        return axiosClient.put(url,body);
    },
    
    deleteCompany: (id) => {
        const url = `/api/v2/admin/companies/${id}`;
        return axiosClient.delete(url);
    },

    showowner: (phone) => {
        const url = `/api/v2/admin/owner/${phone}`;
        return axiosClient.get(url);
    },

    getCompanyByStaff: (phone) => {
        const url = `/api/v0/common/companies/user/${phone}`;
        return axiosClient.get(url);
    }
}

export default CompanyAPI;