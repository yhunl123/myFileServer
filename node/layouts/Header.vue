<template>
  <div>
    <b-navbar class="navbarCustom" toggleable="lg" type="dark" variant="info">
      <b-navbar-brand to="/">
        <img src="../static/img/main_logo.png" style="width: 50px;">
      </b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item to="/board" class="navLinkItem">게시판</b-nav-item>
          <b-nav-item to="/uploadCenter" class="navLinkItem">업로드</b-nav-item>
          <b-nav-item to="/prototype" class="navLinkItem">실험실</b-nav-item>
        </b-navbar-nav>

        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto">

          <b-nav-item-dropdown class="navLinkItem" right text="메뉴">
            <!-- Using 'button-content' slot -->
<!--            <template #button-content>-->
<!--              <em id="memName">{{ $store.getters.getNowMember.name }}</em>-->
<!--            </template>-->
            <b-dropdown-item to="/login" v-if="!isLogined">
              로그인
            </b-dropdown-item>
<!--              <b-dropdown-item to="/register">-->
<!--                회원가입-->
<!--              </b-dropdown-item>-->
            <b-dropdown-item
              id="show-btn"
              @click="logout"
              v-if="isLogined"
            >로그아웃
            </b-dropdown-item>
            <b-modal
              id="bv-modal-example"
              centered
              hide-footer
              hide-header
              no-close-on-esc
              no-close-on-backdrop
            >
              <div class="d-block text-center">
                <h3>로그아웃 되었습니다.</h3>
              </div>
              <b-button
                class="mt-3"
                block
                @click="$bvModal.hide('bv-modal-example')"
                to="/login"
              >확인
              </b-button>
            </b-modal>
          </b-nav-item-dropdown>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
export default {
  name: 'Header',

  data() {
    return {
      isLogined: false,

    }
  },

  methods: {
    logout() {
      localStorage.clear();
      this.$bvModal.show('bv-modal-example')
    },
  },

  mounted() {
    this.isLogined = !!localStorage.getItem('userToken')
  },
}
</script>

<style scoped>
.navbarCustom {
  background-color: #ff9c00 !important;
  /*background-image: linear-gradient(to bottom right, #ff9c00, #f7eee7);*/
}
.navLinkItem > a {
  color: #f7eee7 !important;
}
</style>
