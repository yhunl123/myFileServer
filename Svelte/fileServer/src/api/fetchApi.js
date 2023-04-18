const baseUrl = 'http://localhost:8089'

export const get = (url, params) => {
    const option = {
        method: 'GET',
        mode: 'cors', // no-cors, *cors, same-origin
        params: params,
    }
    return new Promise((resolve, reject) => {
        fetch(baseUrl + url, option)
            .then(response => resolve(response.json()))
            .catch(error => reject(error));
    })
}


export const post = (url, params) => {
    const option = {
        method: 'POST',
        mode: 'cors', // no-cors, *cors, same-origin
        params: params,
    }
    return new Promise((resolve, reject) => {
        fetch(baseUrl + url, option)
            .then(response => resolve(response.json()))
            .catch(error => reject(error));
    })
}

