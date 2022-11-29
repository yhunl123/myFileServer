<template>
  <div id="app" :key="$route.fullPath">
    <Header />
    <Nuxt/>
<!--    <Footer />-->
  </div>
</template>

<script>
import Header from './Header';
import Footer from './Footer';

export default {
  name: 'default',
  components: {
    Footer,
    Header,
  },

}
</script>

<style scoped>

</style>
