import axios from "axios";

const instance = axios.create({

})

instance.interceptors.request.use(
  (config) => {
    config.headers.common['user-token'] = localStorage.getItem('userToken');

    return config;
  }
)


instance.interceptors.response.use(
  (response) => {

  }
)
