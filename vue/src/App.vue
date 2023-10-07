<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>

  export default {
  components: {},
    name: 'App',
  }
</script>
<style >
@font-face {
  font-family: 'Orelega One';
  src: url('~@/assets/fonts/OrelegaOne-Regular.ttf') format('truetype');
}
@font-face {
  font-family: 'Inter';
  src: url('~@/assets/fonts/Inter.ttf');
}

body, html{
  margin:0px;
  background-color:  #182935;
}



</style>