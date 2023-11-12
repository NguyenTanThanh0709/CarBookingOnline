import axios from 'axios';
import queryString from 'query-string';

const axiosClient1 = axios.create({
  paramsSerializer: params => queryString.stringify(params),
});

// Interceptor để thêm token từ cookies và header 'Content-Type: application/json' vào mỗi yêu cầu
axiosClient1.interceptors.request.use(
  (config) => {

    config.headers['Content-Type'] = 'application/json';


    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

axiosClient1.interceptors.response.use(
  (response) => {
    if (response && response.data) {
      return response.data;
    }
    return response;
  },
  (error) => {
    // Xử lý lỗi
    throw error;
  }
);

export default axiosClient1;
