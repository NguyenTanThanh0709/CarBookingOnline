import axiosClient1 from "~/api/axiosClient1";

const AuthenAndAuthorAPI = {
    register: (body) => {
        const url = '/api/v1/auth/register';
        return axiosClient1.post(url, body);
    },
    authenticate: (body) => {
        const url = '/api/v1/auth/authenticate';
        return axiosClient1.post(url, body);
    },
    refreshtoken: () => {
        const url = '/api/v1/auth/refresh-token';
        return axiosClient1.post(url);
    },
    logout :() =>{
        const url = '/api/v1/auth/logout';
        return axiosClient1.get(url);
    }
}

export default AuthenAndAuthorAPI;