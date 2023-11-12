import axiosClient from "~/api/axiosClient";

const TypeCarAPI = {
    getlist: () => {
        const url = '/api/v2/admin/typecar';
        return axiosClient.get(url);
    },
    getone: (id) => {
        const url = `/api/v2/admin/typecar/${id}`;
        return axiosClient.get(url);
    },

    addone: (body) => {
        const url = `/api/v2/admin/typecar`;
        return axiosClient.post(url,body);
    },
    update: (id,body) => {
        const url = `/api/v2/admin/typecar/${id}`;
        return axiosClient.put(url,body);
    },

}

export default TypeCarAPI;