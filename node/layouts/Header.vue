<template>
  <div>
    <b-navbar toggleable="lg" type="dark" variant="info">
      <b-navbar-brand to="/">
        <img src="../static/img/main_logo.png" style="width: 50px;">
      </b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item to="/board">게시판</b-nav-item>
          <b-nav-item to="/memberList">회원목록</b-nav-item>
          <b-nav-item to="/prototype">실험실</b-nav-item>
        </b-navbar-nav>

        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto">

          <b-nav-item-dropdown right text="메뉴">
            <!-- Using 'button-content' slot -->
<!--            <template #button-content>-->
<!--              <em id="memName">{{ $store.getters.getNowMember.name }}</em>-->
<!--            </template>-->
            <div>
              <b-dropdown-item to="/login">
                로그인
              </b-dropdown-item
              >
              <b-dropdown-item to="/register">
                회원가입
              </b-dropdown-item
              >
            </div>
            <div id="logout" v-if="false">
              <b-dropdown-item
                id="show-btn"
                @click="$bvModal.show('bv-modal-example')"
              >로그아웃
              </b-dropdown-item
              >
              <b-modal id="bv-modal-example" hide-footer hide-header>
                <div class="d-block text-center">
                  <h3>로그아웃 되었습니다.</h3>
                </div>
                <b-button
                  class="mt-3"
                  block
                  @click="$bvModal.hide('bv-modal-example')"
                  href="/"
                >확인
                </b-button
                >
              </b-modal>
            </div>
          </b-nav-item-dropdown>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
export default {
  name: 'Header'
}
</script>

<style scoped>
.bg-info {
  background-color: #f4964e;
}
</style>
