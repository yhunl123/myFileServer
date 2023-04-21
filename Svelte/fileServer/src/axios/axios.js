import axios from 'axios';

const instance = axios.create({
    baseURL: 'http://192.168.0.16:3000',
});

instance.interceptors.request.use((config) => {
    if(localStorage.getItem('userToken')) {
        config.headers.Authorization = localStorage.getItem('userToken');
    }

    return config;
}, (error) => {
    return Promise.reject(error);
});

export default instance;
