export default function ({$axios, redirect}) {
  $axios.onRequest((config) => {
    if(localStorage.getItem('userToken')) {
      config.headers.common['user-token'] = localStorage.getItem('userToken');
    }
  });

  $axios.onError((error) => {
    console.log('Internet server error :>>>> ', error);
  })
}
