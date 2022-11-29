import jwtDecode from "jwt-decode";

// 로그인 검증
export default ({store, redirect, error, route}) => {
  // 토큰 유무 검증
  if(!localStorage.getItem('userToken')) {
    if(route.path === '/login') {
      return;
    }
    return redirect('/login');
  } else {
    const token = localStorage.getItem('userToken');
    const decodeToken = jwtDecode(token);

    try {
      // 토큰 검증
      if (decodeToken.jti === "qudtlsemf") {
        // 토큰 만료시
        if (decodeToken.exp < parseInt((new Date().getTime() / 1000).toFixed(0))) {
          localStorage.removeItem('userToken');

          return redirect('/login');
        }
      } else {
        // 토큰 검증 실패
        localStorage.removeItem('userToken');

        return redirect('/login');
      }
    } catch (err) {
      localStorage.removeItem('userToken');

      return redirect('/login');
    }
  }
}
