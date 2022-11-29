<template>
  <div>
    <div class="loginDiv">
      <b-form class="loginForm">
        <b-form-group id="input-group-1" label="아이디" label-for="input-1">
          <b-form-input
            id="input-1"
            placeholder="Enter name"
            required
            v-model="loginForm.memberName"
          ></b-form-input>
        </b-form-group>

        <b-form-group id="input-group-2" label="비밀번호" label-for="input-2">
          <b-form-input
            id="input-2"
            placeholder="Enter password"
            type="password"
            required
            v-model="loginForm.memberPw"
          ></b-form-input>
        </b-form-group>

        <b-button
          @click="login"
          variant="primary"
          style="width: 100%;"
        >
          <span>로그인</span>
          <b-spinner small v-if="isLoading"></b-spinner>
        </b-button>
      </b-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  layout: 'NoHeader',
  components: {

  },

  data() {
    return {
      loginForm: {
        memberName: '',
        memberPw: '',
      },
      isLoading: false,
    }
  },

  methods: {
    login() {
      this.isLoading = true;

      const formData = new FormData();
      formData.append("memberName", this.loginForm.memberName)
      formData.append("memberPw", this.loginForm.memberPw)

      this.$axios.post('/api/login/login', formData)
        .then((res) => {
          console.log(res);
          const data = res.data.data.view;
          if(res.data.success) {
            console.log(data)
            localStorage.setItem('userToken', data.userToken);
          }
        })
        .catch((err) => {
          console.log('login error :>>>> ', err);
        })
        .finally(() => {
          this.isLoading = false;
        })
    },
  },

}
</script>

<style scoped>
  .loginDiv {
    text-align: -webkit-center;
  }
  .loginForm {
    width: 400px;
    text-align: left;
  }
</style>
