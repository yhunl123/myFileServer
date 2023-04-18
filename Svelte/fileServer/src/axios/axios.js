import Axios from 'axios';

// baseURL 설정
const axios = Axios.create({
    baseURL: 'http://localhost:8089',
});

export default axios;
