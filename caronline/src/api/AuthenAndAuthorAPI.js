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
    },
    updateUser : (phone,name,email) =>{
        const url = `/api/v1/demo/update/${phone}?name=${name}&email=${email}`;;
        return axiosClient1.put(url);
    },
    changePassword : (phone, passwordnew, passwordold) =>{
        const url = `/api/v1/demo/password/${phone}?passwordnew=${passwordnew}&passwordold=${passwordold}`;
        return axiosClient1.put(url);
    },

    getOne : (phone) =>{
        const url = `/api/v1/demo/get/${phone}`;
        return axiosClient1.get(url);
    },

    forgotPass : (email) => {
        const url = `/api/v1/demo/forgot?email=${email}`;
        return axiosClient1.post(url);
    }
}

export default AuthenAndAuthorAPI;